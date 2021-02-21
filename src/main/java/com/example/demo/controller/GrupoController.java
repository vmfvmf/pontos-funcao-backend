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

import com.example.demo.model.Grupo;
import com.example.demo.service.GrupoTransacaoService;

@RestController
public class GrupoController {
	
	@Autowired
	private GrupoTransacaoService grupoTransacaoService;
	
	@GetMapping("/grupoTransacao")
	public List<Grupo> findAllGrupoTransacaos(Grupo filtro){
		List<Grupo> f = grupoTransacaoService.findAll(filtro);
		return f;
	}
	@GetMapping("/grupoTransacao/{id}")
	public Optional<Grupo> getGrupoTransacaoById(@PathVariable Integer id){
	        Optional<Grupo> d = grupoTransacaoService.findById(id);
	        return d;
	    }
	
	@PostMapping("/grupoTransacao")
	public Grupo novoGrupoTransacao( @RequestBody Grupo grupoTransacao){
		Grupo s = grupoTransacaoService.save(grupoTransacao);
	        return s;
	    }
		
	 @DeleteMapping("/grupoTransacao/{id}")
	public void deleteGrupoTransacao(@PathVariable Integer id){
		 	grupoTransacaoService.deleteById(id);
	    }
}