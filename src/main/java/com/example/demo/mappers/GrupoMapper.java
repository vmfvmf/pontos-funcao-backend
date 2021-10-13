package com.example.demo.mappers;
import java.util.List;

import com.example.demo.dto.GrupoDto;
import com.example.demo.model.Grupo;

public class GrupoMapper extends AbstractMapperBase<GrupoDto, Grupo> {	
	public Grupo convertToEntity(GrupoDto dto) {
		return super.convertToTarget(dto, Grupo.class);
	}
	
	public GrupoDto convertToDto(Grupo entity) {
		return super.convertToTarget(entity, GrupoDto.class);
	}
	
	public List<Grupo> convertToEntityList(List<GrupoDto> dtos) {
		return super.mapList(dtos, Grupo.class);
	}
	
	public List<GrupoDto> convertToDtoList(List<Grupo> entitys) {
		return super.mapList(entitys, GrupoDto.class);
	}
}
