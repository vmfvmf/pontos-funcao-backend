package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.FuncaoDados;

public interface FuncaoDadosService {
	public List<FuncaoDados> findAll(FuncaoDados filtro);
	public FuncaoDados save(FuncaoDados d);
	public void deleteById(long id);
	public Optional<FuncaoDados> findById(long id);
}
