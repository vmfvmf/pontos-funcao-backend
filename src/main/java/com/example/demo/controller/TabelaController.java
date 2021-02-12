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

import com.example.demo.model.Tabela;
import com.example.demo.service.TabelaService;

@RestController
public class TabelaController {
	
	@Autowired
	private TabelaService tabelaService;
	
	@GetMapping("/tabelas")
	public List<Tabela> findAllTabelas(Tabela filtro){
		List<Tabela> f = tabelaService.findAll(filtro);
		return f;
	}
	@GetMapping("/tabelas/{id}")
	public Optional<Tabela> getTabelaById(@PathVariable Integer id){
	        Optional<Tabela> d = tabelaService.findById(id);
	        return d;
	    }
	
	@PostMapping("/tabelas")
	public Tabela novoTabela( @RequestBody Tabela tabela){
		Tabela s = tabelaService.save(tabela);
	        return s;
	    }
	
	@PutMapping("/tabelas")
	public Tabela updateTabela( @RequestBody Tabela tabela){
		Tabela s = tabelaService.save(tabela);
	        return s;
	    }
	
	 @DeleteMapping("/tabelas/{id}")
	public void deleteTabela(@PathVariable Integer id){
		 	tabelaService.deleteById(id);
	    }

}
