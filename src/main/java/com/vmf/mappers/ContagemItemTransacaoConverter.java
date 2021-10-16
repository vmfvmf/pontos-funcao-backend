package com.vmf.mappers;
import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemItemTransacaoDto;
import com.vmf.entities.ContagemItemTransacao;
import com.vmf.service.ContagemItemTransacaoService;

@Service("contagemItemTransacaoConverter")
public class ContagemItemTransacaoConverter extends AbstractMapperBase<ContagemItemTransacaoDto, ContagemItemTransacao> {
	
	@Autowired
	private ContagemItemTransacaoService service; 
	
	@Autowired
	TransacaoTDMapperConverter transacaoTDMapperConverter;
	
	public ContagemItemTransacaoConverter () {
		super();
	}
	
	@PostConstruct
	private void init() {
		getModelMapper().addConverter(transacaoTDMapperConverter.convertToDto());
		getModelMapper().addConverter(transacaoTDMapperConverter.convertToEntity());
	}

	public AbstractConverter<ContagemItemTransacao, ContagemItemTransacaoDto> convertToDto() {
		return new AbstractConverter<ContagemItemTransacao, ContagemItemTransacaoDto>(){			
			@Override
			protected ContagemItemTransacaoDto convert(ContagemItemTransacao source) {
				ContagemItemTransacaoDto dto = getModelMapper().map(source, ContagemItemTransacaoDto.class);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<ContagemItemTransacaoDto, ContagemItemTransacao> convertToEntity() {
		return new AbstractConverter<ContagemItemTransacaoDto, ContagemItemTransacao>(){
			@Override
			protected ContagemItemTransacao convert(ContagemItemTransacaoDto source) {
				ContagemItemTransacao entidade = getModelMapper().map(source, ContagemItemTransacao.class);
				if (entidade.getId() != null) {
					ContagemItemTransacao origem = service.findById(entidade.getId()).get();
					entidade.setContagemItemOrigem(origem.getEntidadeOrigem());
				}
				return entidade;
			}			
					
		};
	}	
	
	@Override
	public ContagemItemTransacao convertToEntity(ContagemItemTransacaoDto dto) {
		return convertToTarget(dto, ContagemItemTransacao.class);
	}

	@Override
	public ContagemItemTransacaoDto convertToDto(ContagemItemTransacao entity) {
		return convertToTarget(entity, ContagemItemTransacaoDto.class);
	}
}
