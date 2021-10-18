package com.vmf.cpf.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vmf.cpf.interfaces.ICustomCallback;
import com.vmf.cpf.interfaces.IGetId;
import com.vmf.cpf.interfaces.IHaveCriadoModificadoId;
import com.vmf.cpf.interfaces.IHaveEntidadeOrigem;
import com.vmf.cpf.interfaces.IValidator;
import com.vmf.cpf.mappers.AbstractMapperBase;

public abstract class AbstractService<D, E> {
	@Autowired
	protected JpaRepository<E, Long> repository;
					
	private IValidator<E> validator;
	private ICustomCallback<E> beforeSaveCallback;
	private ICustomCallback<E> afterSaveCallback;
	
	public abstract AbstractMapperBase<D, E> getMapper();
	
	@Bean
	public AbstractService<D, E> getAbstractService() {
		return this;
	}
	
	public void setValidator(IValidator<E> validator) {
		this.validator = validator;
	}
	
	public void setBeforeSaveCallback(ICustomCallback<E> customCallback) {
		this.beforeSaveCallback = customCallback;
	}
	
	public void setAfterSaveCallback(ICustomCallback<E> customCallback) {
		this.afterSaveCallback = customCallback;
	}
	
	public List<E> findAll() {
		return repository.findAll();
	}

	public E save(E entity) throws Exception {
		try {
			validate(entity);
			prepareToSave(entity);
			executeBeforeSaveCallback(entity);
			E e = repository.save(entity);
			e = executeAfterSaveCallback(e);
			return e;
		}catch(Exception error) {
			validator = null;
			beforeSaveCallback = null;
			afterSaveCallback = null;
			throw error;
		}	
	}
	
	private void validate(E entity) throws Exception {
		if (validator != null) {
			validator.validate(entity);
		}
	}
	
	private void executeBeforeSaveCallback(E entity) throws Exception {
		if (beforeSaveCallback != null) {
			beforeSaveCallback.execute(entity);
		}
	}
	
	private E executeAfterSaveCallback(E entity) throws Exception {
		if (afterSaveCallback != null) {
			return afterSaveCallback.execute(entity);
		}
		return entity;
	}
	
	public abstract void prepareToSave(E entity);
	
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public Optional<E> findById(long id) {
		return repository.findById(id);
	}	
	
	public List<E> findAll(E filtro) {
		Example<E> ex = Example.of(filtro, ExampleMatcher.matching());
		List<E> list = repository.findAll(ex);
		return list;
	}
	
	protected void setCriadoModificadoId(IHaveCriadoModificadoId entity) {
		entity.setId(null);
		entity.setCriado(LocalDate.now());
		entity.setModificado(null);
	}
	
	@SuppressWarnings("unchecked")
	public E findOrigemDaSelecionada(Long atualEntidadeOrigemId, List<E> compararOrigem) {
		Optional<E> origem = findById(atualEntidadeOrigemId);
		while (origem.isPresent()) {
			final E origem2 = origem.get();
			Optional<E> achouOrigem = compararOrigem.stream()
					.filter(pred -> ((IGetId)pred).getId() == ((IGetId)origem2).getId()).findFirst();
			if (achouOrigem.isPresent()) {
				return achouOrigem.get();
			}
			origem = Optional.ofNullable((E)((IHaveEntidadeOrigem<E>)origem2).getEntidadeOrigem());
		}
		return null;
	}
	
	public Boolean verificaSeAnteriorEhAnterior(IHaveEntidadeOrigem<E> atualOrigem, E anterior) {
		if (atualOrigem == null) {
			return false;
		}
		if (atualOrigem.equals(anterior)) {
			return true;
		}
		return verificaSeAnteriorEhAnterior(((IHaveEntidadeOrigem<E>)atualOrigem.getEntidadeOrigem()), anterior);
	}
	
	public Boolean verificaSeAnteriorEhAnterior(Long anteriorId, IHaveEntidadeOrigem<E> atualOrigem) {
		if (atualOrigem == null) {
			return false;
		}
		if (anteriorId.equals(((IHaveCriadoModificadoId)atualOrigem).getId())) {
			return true;
		}
		return verificaSeAnteriorEhAnterior(anteriorId, ((IHaveEntidadeOrigem<E>)atualOrigem.getEntidadeOrigem()));
	}
}
