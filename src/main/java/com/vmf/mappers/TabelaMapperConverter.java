package com.vmf.mappers;
import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.TabelaDto;
import com.vmf.entities.Tabela;
import com.vmf.service.TabelaService;

@Service("tabelaMapperConverter")
public class TabelaMapperConverter extends AbstractMapperBase<TabelaDto, Tabela> {
	
	@Autowired
	private TabelaService tabelaService; 
	
	@Autowired
	ColunaConverter colunaConverter;
	
	public TabelaMapperConverter () {
		super();
	}
	
	@PostConstruct
	private void init() {
		getModelMapper().addConverter(colunaConverter.convertToDto());
		getModelMapper().addConverter(colunaConverter.convertToEntity());
	}

	public AbstractConverter<Tabela, TabelaDto> convertToDto() {
		return new AbstractConverter<Tabela, TabelaDto>(){			
			@Override
			protected TabelaDto convert(Tabela source) {
				TabelaDto dto = getModelMapper().map(source, TabelaDto.class);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<TabelaDto, Tabela> convertToEntity() {
		return new AbstractConverter<TabelaDto, Tabela>(){
			@Override
			protected Tabela convert(TabelaDto source) {
				Tabela entidade = getModelMapper().map(source, Tabela.class);
				if (entidade.getId() != null) {
					Tabela origem = tabelaService.findById(entidade.getId()).get();
					entidade.setTabelaOrigem(origem.getTabelaOrigem());
				}
				return entidade;
			}			
					
		};
	}

	@Override
	public Tabela convertToEntity(TabelaDto dto) {
		return convertToTarget(dto, Tabela.class);
	}

	@Override
	public TabelaDto convertToDto(Tabela entity) {
		return convertToTarget(entity, TabelaDto.class);
	}	
}