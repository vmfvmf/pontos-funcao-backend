package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Sistema;

public interface SistemaService {
	public List<Sistema> findAll();
	public Sistema save(Sistema s);
	public void deleteById(long id);
	public Optional<Sistema> findById(long id);
}
