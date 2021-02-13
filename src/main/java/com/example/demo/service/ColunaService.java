package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Coluna;
import com.example.demo.model.Tabela;

public interface ColunaService {
	public List<Coluna> findAll(Coluna filtro);
	public Coluna save(Coluna d);
	public void deleteById(long id);
	public Optional<Coluna> findById(long id);
	public List<Coluna> salvaEmLote(List<Coluna> colunas);
}