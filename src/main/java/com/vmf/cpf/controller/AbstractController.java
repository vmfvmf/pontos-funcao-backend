package com.vmf.cpf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.vmf.cpf.service.AbstractService;

public abstract class AbstractController<D, E> {		
	protected abstract AbstractService<D, E> getService();
	
	protected List<D> findAll(D filtro) {
		E filtro2 = getService().getMapper().convertToEntity(filtro);
		return getService().getMapper().convertToDtoList(getService().findAll(filtro2));
	}
	
	protected List<D> findAll() {
		return getService().getMapper().convertToDtoList(getService().findAll());
	}

	protected D getEntityById(@PathVariable long id) throws Exception {
		Optional<E> entity = getService().findById(id);
		if (entity.isPresent()) {
			return getService().getMapper().convertToDto(entity.get());
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
			return getService().getMapper().convertToDto(getService().save(entity.get()));
		}
		throw new Exception("Nenhum registro encontrado com o identificador informado.");
	}
	
	private D newEditEntity(D dto) throws Exception {
		E entity = getService().getMapper().convertToEntity(dto);
		entity = getService().save(entity);
		return getService().getMapper().convertToDto(entity);
	}

	protected void deleteEntity(@PathVariable Long id) {
		getService().deleteById(id);
	}
}
