package com.vmf.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vmf.interfaces.ICustomCallback;
import com.vmf.interfaces.IHaveCriadoModificadoId;
import com.vmf.interfaces.IValidator;

public abstract class AbstractService<T> {
	@Autowired
	protected JpaRepository<T, Long> repository;
				
	private IValidator<T> validator;
	private ICustomCallback<T> beforeSaveCallback;
	private ICustomCallback<T> afterSaveCallback;
	
	public void setValidator(IValidator<T> validator) {
		this.validator = validator;
	}
	
	public void setBeforeSaveCallback(ICustomCallback<T> customCallback) {
		this.beforeSaveCallback = customCallback;
	}
	
	public void setAfterSaveCallback(ICustomCallback<T> customCallback) {
		this.afterSaveCallback = customCallback;
	}
	
	public List<T> findAll() {
		return repository.findAll();
	}

	public T save(T entity) throws Exception {
		try {
			validate(entity);
			prepareToSave(entity);
			executeBeforeSaveCallback(entity);
			T t = repository.save(entity);
			t = executeAfterSaveCallback(t);
			return t;
		}catch(Exception e) {
			validator = null;
			beforeSaveCallback = null;
			afterSaveCallback = null;
			throw e;
		}	
	}
	
	private void validate(T entity) throws Exception {
		if (validator != null) {
			validator.validate(entity);
		}
	}
	
	private void executeBeforeSaveCallback(T entity) throws Exception {
		if (beforeSaveCallback != null) {
			beforeSaveCallback.execute(entity);
		}
	}
	
	private T executeAfterSaveCallback(T entity) throws Exception {
		if (afterSaveCallback != null) {
			return afterSaveCallback.execute(entity);
		}
		return entity;
	}
	
	public abstract void prepareToSave(T entity);
	
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public Optional<T> findById(long id) {
		return repository.findById(id);
	}	
	
	public List<T> findAll(T filtro) {
		Example<T> ex = Example.of(filtro, ExampleMatcher.matching());
		List<T> list = repository.findAll(ex);
		return list;
	}
	
	protected void setCriadoModificadoId(IHaveCriadoModificadoId entity) {
		entity.setId(null);
		entity.setCriado(LocalDate.now());
		entity.setModificado(null);
	}
}
