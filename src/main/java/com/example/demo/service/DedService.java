package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Ded;

public interface DedService {
	public List<Ded> findAll();
	public Ded save(Ded d);
	public void deleteById(long id);
	public Optional<Ded> findById(long id);
}
