package com.vmf.mappers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ProjetoDto;
import com.vmf.entities.Projeto;
import com.vmf.service.ProjetoService;

@Service("projetoMapper")
public class ProjetoMapper extends AbstractMapperBase<ProjetoDto, Projeto> {	
	
	@Autowired
	private ProjetoService service;
	
	public Projeto convertToEntity(ProjetoDto dto) {
		return super.convertToTarget(dto, Projeto.class);
	}
	
	public ProjetoDto convertToDto(Projeto entity) {
		return super.convertToTarget(entity, ProjetoDto.class);
	}
	
	public List<Projeto> convertToEntityList(List<ProjetoDto> dtos) {
		return super.convertToEntityList(dtos);
	}
	
	public List<ProjetoDto> convertToDtoList(List<Projeto> entitys) {
		return super.convertToDtoList(entitys);
	}

	@Override
	public ProjetoService getService() {
		return service;
	}
}
