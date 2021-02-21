package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ded;
import com.example.demo.service.DedService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DedController {

	@Autowired
	private DedService dedService;
	
	@GetMapping("/deds")
	public List<Ded> findAllDeds() {
		return dedService.findAll();
	}

	@GetMapping("/deds/{id}")
	public Optional<Ded> getDedById(@PathVariable long id) {
		Optional<Ded> d = dedService.findById(id);
		return d;
	}

	@PostMapping("/deds")
	public Ded newDed(@RequestBody Ded ded) {
		return dedService.save(ded);
	}

	@DeleteMapping("/deds/{id}")
	public void deleteDed(@PathVariable int id) {
		dedService.deleteById(id);
	}


}
