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

import com.example.demo.model.GrupoTransacao;
import com.example.demo.service.GrupoTransacaoService;

@RestController
public class GrupoTransacaoController {
	
	@Autowired
	private GrupoTransacaoService grupoTransacaoService;
	
	@GetMapping("/grupoTransacao")
	public List<GrupoTransacao> findAllGrupoTransacaos(GrupoTransacao filtro){
		List<GrupoTransacao> f = grupoTransacaoService.findAll(filtro);
		return f;
	}
	@GetMapping("/grupoTransacao/{id}")
	public Optional<GrupoTransacao> getGrupoTransacaoById(@PathVariable Integer id){
	        Optional<GrupoTransacao> d = grupoTransacaoService.findById(id);
	        return d;
	    }
	
	@PostMapping("/grupoTransacao")
	public GrupoTransacao novoGrupoTransacao( @RequestBody GrupoTransacao grupoTransacao){
		GrupoTransacao s = grupoTransacaoService.save(grupoTransacao);
	        return s;
	    }
	
	@PutMapping("/grupoTransacao")
	public GrupoTransacao updateGrupoTransacao( @RequestBody GrupoTransacao grupoTransacao){
		GrupoTransacao s = grupoTransacaoService.save(grupoTransacao);
	        return s;
	    }
	
	 @DeleteMapping("/grupoTransacao/{id}")
	public void deleteGrupoTransacao(@PathVariable Integer id){
		 	grupoTransacaoService.deleteById(id);
	    }
}