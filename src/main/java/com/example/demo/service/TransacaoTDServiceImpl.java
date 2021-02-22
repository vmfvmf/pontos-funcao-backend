package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.TransacaoTD;
import com.example.demo.repository.TransacaoTDRepository;

@Service("transacaoTDService")
public class TransacaoTDServiceImpl implements TransacaoTDService{

	@Autowired
	private TransacaoTDRepository transacaoTDRepository;

	@Override
	public TransacaoTD save(TransacaoTD tabela) {
		return transacaoTDRepository.save(tabela);
	}

	@Override
	public void deleteById(long id) {
		transacaoTDRepository.deleteById(id);
	}

	@Override
	public List<TransacaoTD> findAll(TransacaoTD filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<TransacaoTD> ex = Example.of(filtro, mat);
		return transacaoTDRepository.findAll(ex);
	}

	@Override
	public Optional<TransacaoTD> findById(long id) {
		return transacaoTDRepository.findById(id);
	}
}
