package com.vmf.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

import com.vmf.service.AbstractService;

public abstract class AbstractMapperBase<D, E> {
	private ModelMapper modelMapper;
		
	public AbstractMapperBase() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
	  .setFieldMatchingEnabled(true)
	  .setFieldAccessLevel(AccessLevel.PRIVATE)
	  .setAmbiguityIgnored(true)
	  .setSkipNullEnabled(true);
	}
	
	public abstract AbstractService<D, E> getService();
	
	public ModelMapper getModelMapper() {
	    return modelMapper;
	}
	
	public <S, T> T convertToTarget(S src, Class<T> targetClass) {
        return modelMapper.map(src, targetClass);
    }
	
	public abstract E convertToEntity(D dto);
	
	public abstract D convertToDto(E entity);
		
	public List<E> convertToEntityList(List<D> dtoList) {
		return dtoList
			      .stream()
			      .map(this::convertToEntity)
			      .collect(Collectors.toList());
	}
	
	public List<D> convertToDtoList(List<E> entityList) {
		return entityList
			      .stream()
			      .map(this::convertToDto)
			      .collect(Collectors.toList());
	}
		
}
