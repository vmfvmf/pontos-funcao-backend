package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Sprint;



public interface SprintService {
	public List<Sprint> findAll(Sprint flitro);
	public Sprint save(Sprint s);
	public void deleteById(Integer id);
	public Optional<Sprint> findById(Integer id);
	 
}
