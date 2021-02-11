package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ded;
import com.example.demo.repository.DedRepository;

@Service("dedService")
public class DedServiceImpl implements DedService{

	@Autowired
	private DedRepository dedRepository;
		
	@Override
	public List<Ded> findAll() {
		return dedRepository.findAll();
	}

	@Override
	public Ded save(Ded d) {
		return dedRepository.save(d);
	}
	
	@Override
	public void deleteById(long id) {
		dedRepository.deleteById(id);
	}

	@Override
	public Optional<Ded> findById(long id) {
		return dedRepository.findById(id);
	}


}
