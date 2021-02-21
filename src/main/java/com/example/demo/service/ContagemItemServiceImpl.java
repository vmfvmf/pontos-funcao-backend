package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.ContagemItem;
import com.example.demo.repository.ContagemItemRepository;

@Service("contagemItemService")
public class ContagemItemServiceImpl implements ContagemItemService{
	
	 @PersistenceContext
	    public EntityManager em;

	@Autowired
	private ContagemItemRepository contagemItemRepository;
		
	@Override
	public List<ContagemItem> findAll(ContagemItem filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<ContagemItem> ex = Example.of(filtro, mat);
		return contagemItemRepository.findAll(ex);
	}

	@Override
	public ContagemItem save(ContagemItem d) {
		return contagemItemRepository.save(d);
	}
	
	@Override
	public void deleteById(long id) {
		contagemItemRepository.deleteById(id);
	}

	@Override
	public Optional<ContagemItem> findById(long id) {
		return contagemItemRepository.findById(id);
	}
}
