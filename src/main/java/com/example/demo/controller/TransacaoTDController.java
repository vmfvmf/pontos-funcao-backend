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

import com.example.demo.model.TransacaoTD;
import com.example.demo.service.TransacaoTDService;

@RestController
public class TransacaoTDController {

	@Autowired
	private TransacaoTDService transacaoTDService;

	@GetMapping("/transacaotd")
	public List<TransacaoTD> findAll(TransacaoTD filtro) {
		return transacaoTDService.findAll(filtro);
	}
	
	@GetMapping("/transacaotd/{id}")
	public Optional<TransacaoTD> getById(@PathVariable Integer id) {
		return transacaoTDService.findById(id);
	}
	
	@PostMapping("/transacaotd")	
	public TransacaoTD save(@RequestBody TransacaoTD item) {
		TransacaoTD salvo = transacaoTDService.save(item);
		return salvo;
	}
	

	@DeleteMapping("/transacaotd/{id}")
	public void delete(@PathVariable Integer id) {
		transacaoTDService.deleteById(id);
	}

}
