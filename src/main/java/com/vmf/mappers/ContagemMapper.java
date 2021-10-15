package com.vmf.mappers;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemDto;
import com.vmf.entities.Contagem;
import com.vmf.enums.ContagemEscopoEnum;
import com.vmf.service.ContagemService;

@Service("contagemMapper")
public class ContagemMapper extends AbstractMapperBase<ContagemDto, Contagem> {
	@Autowired
	private ContagemService service;
	
	@Autowired
	private ContagemItemArquivoReferenciadoConverter contagemItemArquivoReferenciadoConverter;
	
	@Autowired
	private ContagemItemTransacaoConverter contagemItemTransacaoConverter;
	
	@PostConstruct
	private void init() {
		getModelMapper().addConverter(contagemItemArquivoReferenciadoConverter.convertToDto());
		getModelMapper().addConverter(contagemItemTransacaoConverter.convertToDto());
		
		getModelMapper().addConverter(contagemItemArquivoReferenciadoConverter.convertToEntity());
		getModelMapper().addConverter(contagemItemTransacaoConverter.convertToEntity());
	}
	
	public ContagemMapper () {
		super();		
	}
	
	public Contagem convertToEntity(ContagemDto dto) {
		Contagem convertida = super.convertToTarget(dto, Contagem.class);
		if (dto.getId() != null) {
			Contagem original = service.findById(dto.getId()).get();
			convertida.setContagemOrigem(original.getContagemOrigem());
		}
		return convertida;
	}
	
	public ContagemDto convertToDto(Contagem entity) {
		ContagemDto dto = super.convertToTarget(entity, ContagemDto.class);
		if (dto.getEscopo() == ContagemEscopoEnum.SPRINT) {
			dto.setProjeto(dto.getSprint().getProjeto());
		}
		return dto;
	}
		
}
