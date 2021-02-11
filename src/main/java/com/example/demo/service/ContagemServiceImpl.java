package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.enums.EscopoContagemEnum;
import com.example.demo.model.Contagem;
import com.example.demo.model.Sistema;
import com.example.demo.model.Sprint;
import com.example.demo.repository.ContagemRepository;

@Service("contagemService")
public class ContagemServiceImpl implements ContagemService{
	
	 @PersistenceContext
	    public EntityManager em;

	@Autowired
	private ContagemRepository contagemRepository;
		
	@Override
	public List<Contagem> findAll(Contagem filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<Contagem> ex = Example.of(filtro, mat);
		return contagemRepository.findAll(ex);
	}

	@Override
	public Contagem save(Contagem d) {
		return contagemRepository.save(d);
	}
	
	@Override
	public void deleteById(long id) {
		contagemRepository.deleteById(id);
	}

	@Override
	public Optional<Contagem> findById(long id) {
		return contagemRepository.findById(id);
	}

	@Override
	public Sistema findSistemaByContagemId(long id) {
		return em.createNamedQuery("Sistema.findByContagemId", Sistema.class).setParameter(1, id).getSingleResult();
	}
	
	@Override
	public List<Contagem> findContagensSistemas() {
		List<Contagem> c = em.createNamedQuery("Contagem.joinSistema", Contagem.class).setParameter(1, EscopoContagemEnum.SPRINT).getResultList();
		return c;
	}


}
