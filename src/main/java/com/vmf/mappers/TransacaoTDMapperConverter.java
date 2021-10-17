package com.vmf.mappers;
import java.util.Optional;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.TransacaoTDDto;
import com.vmf.entities.TransacaoTD;
import com.vmf.enums.ContagemDadoSituacaoEnum;
import com.vmf.service.TransacaoTDService;

@Service("transacaoTDMapperConverter")
public class TransacaoTDMapperConverter extends AbstractMapperBase<TransacaoTDDto, TransacaoTD> {
	
	@Autowired
	private TransacaoTDService transacaoTDService; 

	public AbstractConverter<TransacaoTD, TransacaoTDDto> convertToDto() {
		return new AbstractConverter<TransacaoTD, TransacaoTDDto>(){			
			@Override
			protected TransacaoTDDto convert(TransacaoTD source) {
				TransacaoTDDto dto = convertToDto(source);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<TransacaoTDDto, TransacaoTD> convertToEntity() {
		return new AbstractConverter<TransacaoTDDto, TransacaoTD>(){
			@Override
			protected TransacaoTD convert(TransacaoTDDto source) {
				TransacaoTD entidade = convertToEntity(source);
				if (entidade.getId() != null) {
					Optional<TransacaoTD> origem = transacaoTDService.findById(entidade.getId());
					if (origem.isPresent()) {
						entidade.setTransacaoTDOrigem(origem.get().getTransacaoTDOrigem());
					}
				}
				return entidade;
			}			
					
		};
	}

	@Override
	public TransacaoTD convertToEntity(TransacaoTDDto dto) {
		return convertToTarget(dto, TransacaoTD.class);
	}

	@Override
	public TransacaoTDDto convertToDto(TransacaoTD entity) {
		TransacaoTDDto dto = convertToTarget(entity, TransacaoTDDto.class);
		if (dto.getCompararVersao()) {
			if (dto.getEntidadeOrigem() == null) {
				dto.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
			}
		}
		return dto;
	}

	@Override
	public TransacaoTDService getService() {
		return transacaoTDService;
	}	
}