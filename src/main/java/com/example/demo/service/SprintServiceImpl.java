package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sprint;
import com.example.demo.repository.SprintRepository;

@Service("sprintService")
public class SprintServiceImpl implements SprintService{

	@Autowired
	private SprintRepository sprintRepository;

	@Override
	public Sprint save(Sprint sprint) {
		return sprintRepository.save(sprint);
	}

	@Override
	public void deleteById(Integer id) {
		sprintRepository.deleteById((long)id.intValue());
	}

	@Override
	public List<Sprint> findAll(Sprint filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues();
		//withIgnorePaths(paths...)
		Example<Sprint> ex = Example.of(filtro, mat);
		return sprintRepository.findAll(ex);
	}

	@Override
	public Optional<Sprint> findById(Integer id) {
		return sprintRepository.findById((long)id.intValue());
	}


}