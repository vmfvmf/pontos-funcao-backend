package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Transacao;

public interface TransacaoService {
	public List<Transacao> findAll(Transacao filtro);
	public Transacao save(Transacao d);
	public void deleteById(long id);
	public Optional<Transacao> findById(long id);
	public List<Transacao> salvaEmLote(List<Transacao> transacaos);
}
