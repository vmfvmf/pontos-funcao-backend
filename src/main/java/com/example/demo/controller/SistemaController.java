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

import com.example.demo.model.Sistema;
import com.example.demo.service.SistemaService;

@RestController
public class SistemaController {
	
	@Autowired
	private SistemaService sistemaService;
	
	@GetMapping("/sistemas")
	public List<Sistema> findAllSistemas(){
		return sistemaService.findAll();
	}
	@GetMapping("/sistemas/{id}")
	public Optional<Sistema> getSistemaById(@PathVariable long id){
	        Optional<Sistema> d = sistemaService.findById(id);
	        return d;
	    }
	
	@PostMapping("/sistemas")
	public Sistema novoSistema( @RequestBody Sistema sistema){
	        Sistema d = sistemaService.save(sistema);
	        return d;
	    }
	
	 @DeleteMapping("/sistemas/{id}")
	public void deleteSistema(@PathVariable long id){
		 	sistemaService.deleteById(id);
	    }

}
