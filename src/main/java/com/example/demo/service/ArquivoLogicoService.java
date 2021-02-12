package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.ArquivoLogico;

public interface ArquivoLogicoService {
	public List<ArquivoLogico> findAll(ArquivoLogico filtro);
	public ArquivoLogico save(ArquivoLogico d);
	public void deleteById(long id);
	public Optional<ArquivoLogico> findById(long id);
}
