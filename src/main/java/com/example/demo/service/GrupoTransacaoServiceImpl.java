package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.GrupoTransacao;
import com.example.demo.model.Sistema;
import com.example.demo.model.Sprint;
import com.example.demo.repository.GrupoTransacaoRepository;

@Service("grupoTransacaoService")
public class GrupoTransacaoServiceImpl implements GrupoTransacaoService{
	
	@Autowired
	private GrupoTransacaoRepository grupoTransacaoRepository;
		
	@Override
	public List<GrupoTransacao> findAll(GrupoTransacao filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<GrupoTransacao> ex = Example.of(filtro, mat);
		return grupoTransacaoRepository.findAll(ex);
	}

	@Override
	public GrupoTransacao save(GrupoTransacao d) {
		return grupoTransacaoRepository.save(d);
	}
	
	@Override
	public void deleteById(long id) {
		grupoTransacaoRepository.deleteById(id);
	}

	@Override
	public Optional<GrupoTransacao> findById(long id) {
		return grupoTransacaoRepository.findById(id);
	}


}
