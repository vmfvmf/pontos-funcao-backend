package com.vmf.mappers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vmf.dto.SistemaDto;
import com.vmf.entities.Sistema;

@Service("sistemaMapper")
public class SistemaMapper extends AbstractMapperBase<SistemaDto, Sistema>{
	public Sistema convertToEntity(SistemaDto dto) {
		return super.convertToTarget(dto, Sistema.class);
	}
	
	public SistemaDto convertToDto(Sistema entity) {
		return super.convertToTarget(entity, SistemaDto.class);
	}
	
	public List<Sistema> convertToEntityList(List<SistemaDto> dtos) {
		return super.convertToEntityList(dtos);
	}
	
	public List<SistemaDto> convertToDtoList(List<Sistema> entitys) {
		return super.convertToDtoList(entitys);
	}
}
