package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Contagem;
import com.example.demo.model.Sistema;

public interface ContagemService {
	public List<Contagem> findAll(Contagem filtro);
	public Contagem save(Contagem d);
	public void deleteById(long id);
	public Optional<Contagem> findById(long id);
	public Sistema findSistemaByContagemId(long id);
	public List<Contagem> findContagensSistemas();
}
