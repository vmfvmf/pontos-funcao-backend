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

import com.example.demo.model.Contagem;
import com.example.demo.model.Sistema;
import com.example.demo.service.ContagemService;

@RestController
public class ContagemController {

	@Autowired
	private ContagemService contagemService;

	@GetMapping("/contagens")
	public List<Contagem> findAllContagens(Contagem filtro) {
		return contagemService.findAll(filtro);
	}
	
	@GetMapping("/contagensSistemas")
	public List<Contagem> findAllContagens() {
		return contagemService.findContagensSistemas();
	}

	@GetMapping("/contagens/{id}")
	public Optional<Contagem> getContagemById(@PathVariable Integer id) {
		return contagemService.findById(id);
	}
	
	@GetMapping("/contagem/sistema/{id}")
	public Sistema getSistemaByContagemId(@PathVariable Integer id){
	        return contagemService.findSistemaByContagemId(id);
	    }

	@PostMapping("/contagens")
	public Contagem novoSprint(@RequestBody Contagem contagem) {
		return contagemService.save(contagem);
	}

	@PutMapping("/contagens")
	public Contagem updateSprint(@RequestBody Contagem contagem) {
		return contagemService.save(contagem);
	}

	@DeleteMapping("/contagens/{id}")
	public void deleteSprint(@PathVariable Integer id) {
		contagemService.deleteById(id);
	}

}
