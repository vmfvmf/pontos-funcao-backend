package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.mappers.AbstractMapperBase;
import com.example.demo.service.AbstractService;

public abstract class AbstractController<D, E> {
	protected abstract AbstractMapperBase<D, E> getMapper();
	
	protected abstract AbstractService<E> getService();
	
	protected List<D> findAll(D filtro) {
		E filtro2 = getMapper().convertToEntity(filtro);
		return getMapper().convertToDtoList(getService().findAll(filtro2));
	}
	
	protected List<D> findAll() {
		return getMapper().convertToDtoList(getService().findAll());
	}

	protected D getEntityById(@PathVariable long id) throws Exception {
		Optional<E> entity = getService().findById(id);
		if (entity.isPresent()) {
			return getMapper().convertToDto(entity.get());
		}
		throw new Exception("Nenhum registro encontrado com o identificador informado.");
	}

	protected D newEntity(@RequestBody D dto) throws Exception {
		return newEditEntity(dto);
	}
	
	protected D editEntity(@RequestBody D dto) throws Exception {
		return newEditEntity(dto);
	}
	
	protected D editEntity(Long id) throws Exception {
		Optional<E> entity = getService().findById(id);
		if (entity.isPresent()) {
			return getMapper().convertToDto(getService().save(entity.get()));
		}
		throw new Exception("Nenhum registro encontrado com o identificador informado.");
	}
	
	private D newEditEntity(D dto) throws Exception {
		E entity = getMapper().convertToEntity(dto);
		entity = getService().save(entity);
		return getMapper().convertToDto(entity);
	}

	protected void deleteEntity(@PathVariable Long id) {
		getService().deleteById(id);
	}
}
