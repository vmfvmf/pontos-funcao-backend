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

import com.example.demo.model.FuncaoDados;
import com.example.demo.service.FuncaoDadosService;

@RestController
public class FuncaoDadosController {
	
	@Autowired
	private FuncaoDadosService funcaoDadosService;
	
	@GetMapping("/funcaoDados")
	public List<FuncaoDados> findAllFuncaoDadoss(FuncaoDados filtro){
		List<FuncaoDados> f = funcaoDadosService.findAll(filtro);
		return f;
	}
	@GetMapping("/funcaoDados/{id}")
	public Optional<FuncaoDados> getFuncaoDadosById(@PathVariable long id){
	        Optional<FuncaoDados> d = funcaoDadosService.findById(id);
	        return d;
	    }
	
	@PostMapping("/funcaoDados")
	public FuncaoDados novoFuncaoDados( @RequestBody FuncaoDados arquivoLogico){
		FuncaoDados s = funcaoDadosService.save(arquivoLogico);
	        return s;
	    }
	
	@PutMapping("/funcaoDados")
	public FuncaoDados updateFuncaoDados( @RequestBody FuncaoDados arquivoLogico){
		FuncaoDados s = funcaoDadosService.save(arquivoLogico);
	        return s;
	    }
	
	 @DeleteMapping("/funcaoDados/{id}")
	public void deleteFuncaoDados(@PathVariable long id){
		 	funcaoDadosService.deleteById(id);
	    }

}
