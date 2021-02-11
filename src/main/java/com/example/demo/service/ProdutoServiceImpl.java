package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@Service("produtoService")
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Produto findProdutoByNome(String nome) {
		return produtoRepository.findByNome(nome);
	}
	
	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto save(Produto p) {
		return produtoRepository.save(p);
	}

	@Override
	public void deleteById(long id) {
		produtoRepository.deleteById(id);
	}

	@Override
	public Optional<Produto> findById(long id) {
		return produtoRepository.findById(id);
	}

}
