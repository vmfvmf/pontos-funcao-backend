package com.vmf.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapperBase<D, E> {
	@Autowired
    protected ModelMapper modelMapper;
	
	protected <S, T> T convertToTarget(S src, Class<T> targetClass) {
        return modelMapper.map(src, targetClass);
    }
	
	public abstract E convertToEntity(D dto);
	
	public abstract D convertToDto(E entity);
	
	public abstract List<E> convertToEntityList(List<D> dto);
	
	public abstract List<D> convertToDtoList(List<E> entity);
	    
	protected <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
}
