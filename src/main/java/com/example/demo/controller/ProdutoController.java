package com.example.demo.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/produtos")
	public List<Produto> findAllProdutos(){
		return produtoService.findAll();
	}
	
	@GetMapping("/produtos/{id}")
	public Optional<Produto> getProdutoById(@PathVariable long id){
	        Optional<Produto> p = produtoService.findById(id);
	        return p;
	    }

	
	 @DeleteMapping("/produtos/{id}")
	public void deleteProduto(@PathVariable int id){
		 	produtoService.deleteById(id);
	    }

}
