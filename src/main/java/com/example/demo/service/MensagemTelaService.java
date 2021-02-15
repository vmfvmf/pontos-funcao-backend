package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.MensagemTela;

public interface MensagemTelaService {
	public List<MensagemTela> findAll(MensagemTela filtro);
	public MensagemTela save(MensagemTela d);
	public void deleteById(long id);
	public Optional<MensagemTela> findById(long id);
}