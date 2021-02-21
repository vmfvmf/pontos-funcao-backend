package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contagem;
import com.example.demo.service.ContagemService;

@RestController
public class ContagemController {

	@Autowired
	private ContagemService contagemService;

	@GetMapping("/contagens")
	public List<Contagem> findAllContagens(Contagem filtro) {
		return contagemService.findAll(filtro);
	}
	
	@GetMapping("/contagens/{id}")
	public Optional<Contagem> getContagemById(@PathVariable Integer id) {
		return contagemService.findById(id);
	}
	
	@PostMapping("/contagens")
	public Contagem novo(@RequestBody Contagem contagem) {
		return contagemService.save(contagem);
	}

	@DeleteMapping("/contagens/{id}")
	public void delete(@PathVariable Integer id) {
		contagemService.deleteById(id);
	}

}
