package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Tabela;

public interface TabelaService {
	public List<Tabela> findAll(Tabela filtro);
	public Tabela save(Tabela d);
	public void deleteById(long id);
	public Optional<Tabela> findById(long id);
	public List<Tabela> salvaEmLote(List<Tabela> tabelas);
}
