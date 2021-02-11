package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Sprint;
import com.example.demo.service.SprintService;

@RestController
public class SprintController {
	
	@Autowired
	private SprintService sprintService;
	
	@GetMapping("/sprints")
	public List<Sprint> findAllSprints(Sprint filtro){
		List<Sprint> f = sprintService.findAll(filtro);
		return f;
	}
	@GetMapping("/sprints/{id}")
	public Optional<Sprint> getSprintById(@PathVariable Integer id){
	        Optional<Sprint> d = sprintService.findById(id);
	        return d;
	    }
	
	@PostMapping("/sprints")
	public Sprint novoSprint( @RequestBody Sprint sprint){
		Sprint s = sprintService.save(sprint);
	        return s;
	    }
	
	@PutMapping("/sprints")
	public Sprint updateSprint( @RequestBody Sprint sprint){
		Sprint s = sprintService.save(sprint);
	        return s;
	    }
	
	 @DeleteMapping("/sprints/{id}")
	public void deleteSprint(@PathVariable Integer id){
		 	sprintService.deleteById(id);
	    }

}
