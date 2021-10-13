package com.vmf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vmf.interfaces.ICustomCallback;
import com.vmf.interfaces.IValidator;

public abstract class AbstractService<T> {
	@Autowired
	protected JpaRepository<T, Long> repository;
				
	private IValidator<T> validator;
	private ICustomCallback<T> customCallback;
	
	public void setValidator(IValidator<T> validator) {
		this.validator = validator;
	}
	
	public void setCustomCallback(ICustomCallback<T> customCallback) {
		this.customCallback = customCallback;
	}
	
	public List<T> findAll() {
		return repository.findAll();
	}

	public T save(T entity) throws Exception {
		validate(entity);
		prepareToSave(entity);
		executeCustomCallback(entity);
		return repository.save(entity);
	}
	
	private void validate(T entity) throws Exception {
		if (validator != null) {
			validator.validate(entity);
			validator = null;
		}
	}
	
	private void executeCustomCallback(T entity) {
		if (customCallback != null) {
			customCallback.execute(entity);
			customCallback = null;
		}
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
}
