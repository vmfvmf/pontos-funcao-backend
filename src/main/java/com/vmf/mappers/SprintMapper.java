package com.vmf.mappers;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vmf.dto.SprintDto;
import com.vmf.entities.Sprint;

@Service("sprintMapper")
public class SprintMapper extends AbstractMapperBase<SprintDto, Sprint> {		
	public Sprint convertToEntity(SprintDto dto) {
		return super.convertToTarget(dto, Sprint.class);
	}
	
	public SprintDto convertToDto(Sprint entity) {
		return super.convertToTarget(entity, SprintDto.class);
	}
	
	public List<Sprint> convertToEntityList(List<SprintDto> dtos) {
		return super.convertToEntityList(dtos);
	}
	
	public List<SprintDto> convertToDtoList(List<Sprint> entitys) {
		return super.convertToDtoList(entitys);
	}
}
