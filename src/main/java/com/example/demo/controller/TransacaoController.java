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

import com.example.demo.model.Transacao;
import com.example.demo.service.TransacaoService;

@RestController
public class TransacaoController {
	
	@Autowired
	private TransacaoService transacaoService;
	
	@GetMapping("/transacaos")
	public List<Transacao> findAllTransacaos(Transacao filtro){
		List<Transacao> f = transacaoService.findAll(filtro);
		return f;
	}
	@GetMapping("/transacaos/{id}")
	public Optional<Transacao> getTransacaoById(@PathVariable Integer id){
	        Optional<Transacao> d = transacaoService.findById(id);
	        return d;
	    }
	
	@PostMapping("/transacaos")
	public Transacao novoTransacao( @RequestBody Transacao transacao){
		Transacao s = transacaoService.save(transacao);
	        return s;
	    }
	
	@PostMapping("/transacaos/emlote")
	public List<Transacao> salvaEmLote( @RequestBody List<Transacao> transacaos){
		return transacaoService.salvaEmLote(transacaos);
	    }
	
	@PutMapping("/transacaos")
	public Transacao updateTransacao( @RequestBody Transacao transacao){
		Transacao s = transacaoService.save(transacao);
	        return s;
	    }
	
	 @DeleteMapping("/transacaos/{id}")
	public void deleteTransacao(@PathVariable Integer id){
		 	transacaoService.deleteById(id);
	    }

}
