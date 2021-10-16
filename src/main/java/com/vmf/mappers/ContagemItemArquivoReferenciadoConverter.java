package com.vmf.mappers;
import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemItemArquivoReferenciadoDto;
import com.vmf.entities.ContagemItemArquivoReferenciado;
import com.vmf.service.ContagemItemArquivoReferenciadoService;

@Service("contagemItemArquivoReferenciadoConverter")
public class ContagemItemArquivoReferenciadoConverter extends AbstractMapperBase<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado> {
	@Autowired
	private ContagemItemArquivoReferenciadoService service; 
	
	@Autowired 
	private TabelaMapperConverter tabelaMapperConverter;

	public ContagemItemArquivoReferenciadoConverter () {
		super();
	}
	
	@PostConstruct
	private void init() {
		getModelMapper().addConverter(tabelaMapperConverter.convertToDto());
		getModelMapper().addConverter(tabelaMapperConverter.convertToEntity());
	}
	
	public AbstractConverter<ContagemItemArquivoReferenciado, ContagemItemArquivoReferenciadoDto> convertToDto() {
		return new AbstractConverter<ContagemItemArquivoReferenciado, ContagemItemArquivoReferenciadoDto>(){			
			@Override
			protected ContagemItemArquivoReferenciadoDto convert(ContagemItemArquivoReferenciado source) {
				ContagemItemArquivoReferenciadoDto dto = getModelMapper().map(source, ContagemItemArquivoReferenciadoDto.class);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado> convertToEntity() {
		return new AbstractConverter<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado>(){
			@Override
			protected ContagemItemArquivoReferenciado convert(ContagemItemArquivoReferenciadoDto source) {
				ContagemItemArquivoReferenciado entidade = getModelMapper().map(source, ContagemItemArquivoReferenciado.class);
				if (entidade.getId() != null) {
					ContagemItemArquivoReferenciado origem = service.findById(entidade.getId()).get();
					entidade.setContagemItemOrigem(origem.getEntidadeOrigem());
				}
				return entidade;
			}			
					
		};
	}

	@Override
	public ContagemItemArquivoReferenciado convertToEntity(ContagemItemArquivoReferenciadoDto dto) {
		return convertToTarget(dto, ContagemItemArquivoReferenciado.class);
	}

	@Override
	public ContagemItemArquivoReferenciadoDto convertToDto(ContagemItemArquivoReferenciado entity) {
		return convertToTarget(entity, ContagemItemArquivoReferenciadoDto.class);
	}

}
