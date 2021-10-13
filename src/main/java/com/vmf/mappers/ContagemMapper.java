package com.vmf.mappers;
import java.util.List;
import java.util.stream.Collectors;

import com.vmf.dto.ContagemDto;
import com.vmf.model.Contagem;

public class ContagemMapper extends AbstractMapperBase<ContagemDto, Contagem> {	
	public Contagem convertToEntity(ContagemDto dto) {
		return super.convertToTarget(dto, Contagem.class);
	}
	
	public ContagemDto convertToDto(Contagem entity) {
		ContagemDto dto = super.convertToTarget(entity, ContagemDto.class);
		prepareToSend(dto);
		return dto;
	}
	
	public List<Contagem> convertToEntityList(List<ContagemDto> dtos) {
		return super.mapList(dtos, Contagem.class);
	}
	
	public List<ContagemDto> convertToDtoList(List<Contagem> entities) {
		List<ContagemDto> lista = super.mapList(entities, ContagemDto.class);
		return lista.stream().map(this::prepareToSend).collect(Collectors.toList());
	}
	
	private ContagemDto prepareToSend(ContagemDto dto) {
		return dto;
	}
}
