package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.Coluna;
import com.example.demo.repository.ColunaRepository;

@Service("colunaService")
public class ColunaServiceImpl implements ColunaService{

	@Autowired
	private ColunaRepository colunaRepository;

	@Override
	public Coluna save(Coluna sprint) {
		return colunaRepository.save(sprint);
	}

	@Override
	public void deleteById(long id) {
		colunaRepository.deleteById(id);
	}

	@Override
	public List<Coluna> findAll(Coluna filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<Coluna> ex = Example.of(filtro, mat);
		return colunaRepository.findAll(ex);
	}

	@Override
	public Optional<Coluna> findById(long id) {
		return colunaRepository.findById(id);
	}

	@Override
	public List<Coluna> salvaEmLote(List<Coluna> colunas) {
		return colunaRepository.saveAll(colunas);
	}


}