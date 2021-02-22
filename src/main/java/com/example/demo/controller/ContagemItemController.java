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
import com.example.demo.model.ContagemItem;
import com.example.demo.model.Tabela;
import com.example.demo.model.TransacaoTD;
import com.example.demo.service.ContagemItemService;

@RestController
public class ContagemItemController {

	@Autowired
	private ContagemItemService contagemItemService;

	@GetMapping("/contagem_item")
	public List<ContagemItem> findAll(ContagemItem filtro) {
		return contagemItemService.findAll(filtro);
	}
	
	@GetMapping("/contagem_item/{id}")
	public Optional<ContagemItem> getById(@PathVariable Integer id) {
		return contagemItemService.findById(id);
	}
	
	@PostMapping("/contagem_item")	
	public ContagemItem save(@RequestBody ContagemItem item) {
		if(item.getTabelas() != null)
			for(Tabela t: item.getTabelas()) {
				t.setContagemItem(item);
				for(Coluna c : t.getColunas()) {
					c.setTabela(t);
				}
			}
		if(item.getTransacaoTDs() != null) {
			for(TransacaoTD td: item.getTransacaoTDs()) {
				td.setContagemItem(item);
			}		
		}	
		ContagemItem salvo = contagemItemService.save(item);
		return salvo;
	}
	
	@DeleteMapping("/contagem_item/tds/{item_id}")
	public void delete2(@PathVariable Integer item_id) {
		contagemItemService.apagaTdsByContagemItem(item_id);
	}

	@DeleteMapping("/contagem_item/{id}")
	public void delete(@PathVariable Integer id) {
		contagemItemService.deleteById(id);
	}

}
