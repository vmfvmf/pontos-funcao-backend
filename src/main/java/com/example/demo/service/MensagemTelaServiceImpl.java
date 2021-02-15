package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.MensagemTela;
import com.example.demo.repository.MensagemTelaRepository;

@Service("mensagemTelaService")
public class MensagemTelaServiceImpl implements MensagemTelaService{

	@Autowired
	private MensagemTelaRepository mensagemTelaRepository;

	@Override
	public MensagemTela save(MensagemTela sprint) {
		return mensagemTelaRepository.save(sprint);
	}

	@Override
	public void deleteById(long id) {
		mensagemTelaRepository.deleteById(id);
	}

	@Override
	public List<MensagemTela> findAll(MensagemTela filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<MensagemTela> ex = Example.of(filtro, mat);
		return mensagemTelaRepository.findAll(ex);
	}

	@Override
	public Optional<MensagemTela> findById(long id) {
		return mensagemTelaRepository.findById(id);
	}

}