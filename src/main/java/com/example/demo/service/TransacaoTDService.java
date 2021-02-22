package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.TransacaoTD;

public interface TransacaoTDService {
	public List<TransacaoTD> findAll(TransacaoTD filtro);
	public TransacaoTD save(TransacaoTD d);
	public void deleteById(long id);
	public Optional<TransacaoTD> findById(long id);
}
