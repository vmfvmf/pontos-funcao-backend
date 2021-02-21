package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Grupo;


public interface GrupoTransacaoService {
	public List<Grupo> findAll(Grupo filtro);
	public Grupo save(Grupo d);
	public void deleteById(long id);
	public Optional<Grupo> findById(long id);
}