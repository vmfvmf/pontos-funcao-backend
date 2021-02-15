package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.GrupoTransacao;


public interface GrupoTransacaoService {
	public List<GrupoTransacao> findAll(GrupoTransacao filtro);
	public GrupoTransacao save(GrupoTransacao d);
	public void deleteById(long id);
	public Optional<GrupoTransacao> findById(long id);
}