package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.Grupo;
import com.example.demo.repository.GrupoTransacaoRepository;

@Service("grupoTransacaoService")
public class GrupoTransacaoServiceImpl implements GrupoTransacaoService{
	
	@Autowired
	private GrupoTransacaoRepository grupoTransacaoRepository;
		
	@Override
	public List<Grupo> findAll(Grupo filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<Grupo> ex = Example.of(filtro, mat);
		return grupoTransacaoRepository.findAll(ex);
	}

	@Override
	public Grupo save(Grupo d) {
		return grupoTransacaoRepository.save(d);
	}
	
	@Override
	public void deleteById(long id) {
		grupoTransacaoRepository.deleteById(id);
	}

	@Override
	public Optional<Grupo> findById(long id) {
		return grupoTransacaoRepository.findById(id);
	}


}
