package com.vmf.cpf.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.SistemaDto;
import com.vmf.cpf.entities.Sistema;
import com.vmf.cpf.service.SistemaService;

@Service("sistemaMapper")
public class SistemaMapper extends AbstractMapperBase<SistemaDto, Sistema>{
	
	@Autowired
	private SistemaService service; 
	
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

	@Override
	public SistemaService getService() {
		return service;
	}
}
