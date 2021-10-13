package com.example.demo.mappers;
import java.util.List;

import com.example.demo.dto.AbstractContagemItemDto;
import com.example.demo.model.AbstractContagemItem;
import com.example.demo.model.ContagemItemTransacao;

public class AbstractContagemItemMapper extends AbstractMapperBase<AbstractContagemItemDto, AbstractContagemItem> {		
	public ContagemItemTransacao convertToEntity(AbstractContagemItemDto dto) {
		return super.convertToTarget(dto, ContagemItemTransacao.class);
	}
	
	public AbstractContagemItemDto convertToDto(AbstractContagemItem entity) {
		return super.convertToTarget(entity, AbstractContagemItemDto.class);
	}
	
	public List<AbstractContagemItem> convertToEntityList(List<AbstractContagemItemDto> dtos) {
		return super.mapList(dtos, AbstractContagemItem.class);
	}
	
	public List<AbstractContagemItemDto> convertToDtoList(List<AbstractContagemItem> entitys) {
		return super.mapList(entitys, AbstractContagemItemDto.class);
	}
}
