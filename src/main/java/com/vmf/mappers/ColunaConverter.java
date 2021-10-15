package com.vmf.mappers;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ColunaDto;
import com.vmf.entities.Coluna;
import com.vmf.service.ColunaService;

@Service("colunaConverter")
public class ColunaConverter extends AbstractMapperBase<ColunaDto, Coluna> {
	
	@Autowired
	private ColunaService service; 

	public AbstractConverter<Coluna, ColunaDto> convertToDto() {
		return new AbstractConverter<Coluna, ColunaDto>(){			
			@Override
			protected ColunaDto convert(Coluna source) {
				ColunaDto dto = getModelMapper().map(source, ColunaDto.class);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<ColunaDto, Coluna> convertToEntity() {
		return new AbstractConverter<ColunaDto, Coluna>(){
			@Override
			protected Coluna convert(ColunaDto source) {
				Coluna entidade = getModelMapper().map(source, Coluna.class);
				if (entidade.getId() != null) {
					Coluna origem = service.findById(entidade.getId()).get();
					entidade.setColunaOrigem(origem.getColunaOrigem());
				}
				return entidade;
			}			
					
		};
	}

	@Override
	public Coluna convertToEntity(ColunaDto dto) {
		return convertToTarget(dto, Coluna.class);
	}

	@Override
	public ColunaDto convertToDto(Coluna entity) {
		return convertToTarget(entity, ColunaDto.class);
	}	
}
