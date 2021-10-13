package com.vmf.mappers;

import java.util.List;

import com.vmf.dto.SistemaDto;
import com.vmf.model.Sistema;

public class SistemaMapper extends AbstractMapperBase<SistemaDto, Sistema>{
	public Sistema convertToEntity(SistemaDto dto) {
		return super.convertToTarget(dto, Sistema.class);
	}
	
	public SistemaDto convertToDto(Sistema entity) {
		return super.convertToTarget(entity, SistemaDto.class);
	}
	
	public List<Sistema> convertToEntityList(List<SistemaDto> dtos) {
		return super.mapList(dtos, Sistema.class);
	}
	
	public List<SistemaDto> convertToDtoList(List<Sistema> entitys) {
		return super.mapList(entitys, SistemaDto.class);
	}
}
