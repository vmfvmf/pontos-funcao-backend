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

import com.example.demo.model.MensagemTela;
import com.example.demo.service.MensagemTelaService;

@RestController
public class MensagemTelaController {
	
	@Autowired
	private MensagemTelaService mensagemTelaService;
	
	@GetMapping("/mensagemTelas")
	public List<MensagemTela> findAllMensagemTelas(MensagemTela filtro){
		List<MensagemTela> f = mensagemTelaService.findAll(filtro);
		return f;
	}
	@GetMapping("/mensagemTelas/{id}")
	public Optional<MensagemTela> getMensagemTelaById(@PathVariable Integer id){
	        Optional<MensagemTela> d = mensagemTelaService.findById(id);
	        return d;
	    }
	
	
	@PostMapping("/mensagemTelas")
	public MensagemTela novoMensagemTela( @RequestBody MensagemTela mensagemTela) throws Exception{
		MensagemTela s = mensagemTelaService.save(mensagemTela);
	        return s;
	    }
	
	 @DeleteMapping("/mensagemTelas/{id}")
	public void deleteMensagemTela(@PathVariable Integer id){
		 	mensagemTelaService.deleteById(id);
	    }

}