package com.example.demo.mappers;
import java.util.List;

import com.example.demo.dto.ProjetoDto;
import com.example.demo.model.Projeto;

public class ProjetoMapper extends AbstractMapperBase<ProjetoDto, Projeto> {	
	public Projeto convertToEntity(ProjetoDto dto) {
		return super.convertToTarget(dto, Projeto.class);
	}
	
	public ProjetoDto convertToDto(Projeto entity) {
		return super.convertToTarget(entity, ProjetoDto.class);
	}
	
	public List<Projeto> convertToEntityList(List<ProjetoDto> dtos) {
		return super.mapList(dtos, Projeto.class);
	}
	
	public List<ProjetoDto> convertToDtoList(List<Projeto> entitys) {
		return super.mapList(entitys, ProjetoDto.class);
	}
}
