package com.vmf.mappers;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vmf.dto.GrupoDto;
import com.vmf.entities.Grupo;

@Service("GrupoMapper")
public class GrupoMapper extends AbstractMapperBase<GrupoDto, Grupo> {	
	public Grupo convertToEntity(GrupoDto dto) {
		return super.convertToTarget(dto, Grupo.class);
	}
	
	public GrupoDto convertToDto(Grupo entity) {
		return super.convertToTarget(entity, GrupoDto.class);
	}
	
	public List<Grupo> convertToEntityList(List<GrupoDto> dtos) {
		return super.convertToEntityList(dtos);
	}
	
	public List<GrupoDto> convertToDtoList(List<Grupo> entitys) {
		return super.convertToDtoList(entitys);
	}
}
