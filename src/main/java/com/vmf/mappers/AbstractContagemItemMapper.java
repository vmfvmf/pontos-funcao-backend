package com.vmf.mappers;
import java.util.List;

import com.vmf.dto.AbstractContagemItemDto;
import com.vmf.entities.AbstractContagemItem;
import com.vmf.entities.ContagemItemTransacao;

public class AbstractContagemItemMapper extends AbstractMapperBase<AbstractContagemItemDto, AbstractContagemItem> {		
	public ContagemItemTransacao convertToEntity(AbstractContagemItemDto dto) {
		return super.convertToTarget(dto, ContagemItemTransacao.class);
	}
	
	public AbstractContagemItemDto convertToDto(AbstractContagemItem entity) {
		return super.convertToTarget(entity, AbstractContagemItemDto.class);
	}
	
	public List<AbstractContagemItem> convertToEntityList(List<AbstractContagemItemDto> dtos) {
		return super.convertToEntityList(dtos);
	}
	
	public List<AbstractContagemItemDto> convertToDtoList(List<AbstractContagemItem> entitys) {
		return super.convertToDtoList(entitys);
	}
}
