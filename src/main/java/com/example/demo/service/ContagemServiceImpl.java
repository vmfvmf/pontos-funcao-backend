package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contagem;
import com.example.demo.model.Sistema;
import com.example.demo.repository.ContagemRepository;

@Service("contagemService")
public class ContagemServiceImpl implements ContagemService{
	
	 @PersistenceContext
	    public EntityManager em;

	@Autowired
	private ContagemRepository contagemItemRepository;
		
	@Override
	public List<Contagem> findAll(Contagem filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<Contagem> ex = Example.of(filtro, mat);
		return contagemItemRepository.findAll(ex);
	}

	@Override
	public Contagem save(Contagem d) {
		return contagemItemRepository.save(d);
	}
	
	@Override
	public void deleteById(long id) {
		contagemItemRepository.deleteById(id);
	}

	@Override
	public Optional<Contagem> findById(long id) {
		return contagemItemRepository.findById(id);
	}

	@Override
	public Sistema findSistemaByContagemId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contagem> findContagensSistemas() {
		// TODO Auto-generated method stub
		return null;
	}
}
