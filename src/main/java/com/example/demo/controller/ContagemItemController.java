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

import com.example.demo.model.ContagemItem;
import com.example.demo.service.ContagemItemService;
import com.example.demo.service.TabelaService;

@RestController
public class ContagemItemController {

	@Autowired
	private ContagemItemService contagemItemService;
	@Autowired
	private TabelaService tabelaService;

	@GetMapping("/contagem_item")
	public List<ContagemItem> findAll(ContagemItem filtro) {
		return contagemItemService.findAll(filtro);
	}
	
	@GetMapping("/contagem_item/{id}")
	public Optional<ContagemItem> getById(@PathVariable Integer id) {
		return contagemItemService.findById(id);
	}
	
	@PostMapping("/contagem_item")
	public ContagemItem save(@RequestBody ContagemItem contagem) {
		ContagemItem salvo = contagemItemService.save(contagem);
		salvo.setTabelas(contagem.getTabelas());
		
		
		return salvo;
	}

	@DeleteMapping("/contagem_item/{id}")
	public void delete(@PathVariable Integer id) {
		contagemItemService.deleteById(id);
	}

}
