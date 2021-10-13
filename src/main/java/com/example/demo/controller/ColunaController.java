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

import com.example.demo.model.Coluna;
import com.example.demo.service.ColunaService;

@RestController
public class ColunaController {
	
	@Autowired
	private ColunaService colunaService;
	
	@GetMapping("/colunas")
	public List<Coluna> findAllColunas(Coluna filtro){
		List<Coluna> f = colunaService.findAll(filtro);
		return f;
	}
	@GetMapping("/colunas/{id}")
	public Optional<Coluna> getColunaById(@PathVariable Integer id){
	        Optional<Coluna> d = colunaService.findById(id);
	        return d;
	    }
	
	@PostMapping("/colunas")
	public Coluna novoColuna( @RequestBody Coluna coluna) throws Exception{
		Coluna s = colunaService.save(coluna);
	        return s;
	    }
		
	 @DeleteMapping("/colunas/{id}")
	public void deleteColuna(@PathVariable Integer id){
		 	colunaService.deleteById(id);
	    }

}