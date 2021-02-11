package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Produto;

public interface ProdutoService {
	public Produto findProdutoByNome(String nome);
	public List<Produto> findAll();
	public Produto save(Produto p);
	public void deleteById(long id);
	public Optional<Produto> findById(long id);
}
