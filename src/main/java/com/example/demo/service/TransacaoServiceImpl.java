package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;

@Service("transacaoService")
public class TransacaoServiceImpl implements TransacaoService{

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Override
	public Transacao save(Transacao transacao) {
		return transacaoRepository.save(transacao);
	}

	@Override
	public void deleteById(long id) {
		transacaoRepository.deleteById(id);
	}

	@Override
	public List<Transacao> findAll(Transacao filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<Transacao> ex = Example.of(filtro, mat);
		return transacaoRepository.findAll(ex);
	}

	@Override
	public Optional<Transacao> findById(long id) {
		return transacaoRepository.findById(id);
	}

	@Override
	public List<Transacao> salvaEmLote(List<Transacao> transacaos) {
		return transacaoRepository.saveAll(transacaos);
	}


}
