package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sistema;
import com.example.demo.repository.SistemaRepository;

@Service("sistemaService")
public class SistemaServiceImpl implements SistemaService{

	@Autowired
	private SistemaRepository sistemaRepository;

	@Override
	public Sistema save(Sistema sistema) {
		return sistemaRepository.save(sistema);
	}

	@Override
	public void deleteById(long id) {
		sistemaRepository.deleteById(id);
	}

	@Override
	public List<Sistema> findAll() {
		return sistemaRepository.findAll();
	}

	@Override
	public Optional<Sistema> findById(long id) {
		return sistemaRepository.findById(id);
	}


}
