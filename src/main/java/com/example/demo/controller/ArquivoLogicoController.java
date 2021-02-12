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

import com.example.demo.model.ArquivoLogico;
import com.example.demo.service.ArquivoLogicoService;

@RestController
public class ArquivoLogicoController {
	
	@Autowired
	private ArquivoLogicoService arquivoLogicoService;
	
	@GetMapping("/arquivoLogicos")
	public List<ArquivoLogico> findAllArquivoLogicos(ArquivoLogico filtro){
		List<ArquivoLogico> f = arquivoLogicoService.findAll(filtro);
		return f;
	}
	@GetMapping("/arquivoLogicos/{id}")
	public Optional<ArquivoLogico> getArquivoLogicoById(@PathVariable long id){
	        Optional<ArquivoLogico> d = arquivoLogicoService.findById(id);
	        return d;
	    }
	
	@PostMapping("/arquivoLogicos")
	public ArquivoLogico novoArquivoLogico( @RequestBody ArquivoLogico arquivoLogico){
		ArquivoLogico s = arquivoLogicoService.save(arquivoLogico);
	        return s;
	    }
	
	@PutMapping("/arquivoLogicos")
	public ArquivoLogico updateArquivoLogico( @RequestBody ArquivoLogico arquivoLogico){
		ArquivoLogico s = arquivoLogicoService.save(arquivoLogico);
	        return s;
	    }
	
	 @DeleteMapping("/arquivoLogicos/{id}")
	public void deleteArquivoLogico(@PathVariable long id){
		 	arquivoLogicoService.deleteById(id);
	    }

}
