package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.ArquivoLogico;
import com.example.demo.repository.ArquivoLogicoRepository;

@Service("arquivoLogicoService")
public class ArquivoLogicoServiceImpl implements ArquivoLogicoService{

	@Autowired
	private ArquivoLogicoRepository arquivoLogicoRepository;

	@Override
	public ArquivoLogico save(ArquivoLogico arquivoLogico) {
		return arquivoLogicoRepository.save(arquivoLogico);
	}

	@Override
	public void deleteById(long id) {
		arquivoLogicoRepository.deleteById(id);
	}

	@Override
	public List<ArquivoLogico> findAll(ArquivoLogico filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues();
		//withIgnorePaths(paths...)
		Example<ArquivoLogico> ex = Example.of(filtro, mat);
		return arquivoLogicoRepository.findAll(ex);
	}

	@Override
	public Optional<ArquivoLogico> findById(long id) {
		return arquivoLogicoRepository.findById(id);
	}


}
