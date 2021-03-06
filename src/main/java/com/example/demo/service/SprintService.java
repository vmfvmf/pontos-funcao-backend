package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Sprint;



public interface SprintService {
	public List<Sprint> findAll(Sprint filtro);
	public Sprint save(Sprint s);
	public void deleteById(long id);
	public Optional<Sprint> findById(long id);
	 
}
