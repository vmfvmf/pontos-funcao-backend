package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tabela;
import com.example.demo.repository.TabelaRepository;

@Service("tabelaService")
public class TabelaServiceImpl implements TabelaService{

	@Autowired
	private TabelaRepository tabelaRepository;

	@Override
	public Tabela save(Tabela tabela) {
		return tabelaRepository.save(tabela);
	}

	@Override
	public void deleteById(long id) {
		tabelaRepository.deleteById(id);
	}

	@Override
	public List<Tabela> findAll(Tabela filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<Tabela> ex = Example.of(filtro, mat);
		return tabelaRepository.findAll(ex);
	}

	@Override
	public Optional<Tabela> findById(long id) {
		return tabelaRepository.findById(id);
	}

	@Override
	public List<Tabela> salvaEmLote(List<Tabela> tabelas) {
		return tabelaRepository.saveAll(tabelas);
	}


}
