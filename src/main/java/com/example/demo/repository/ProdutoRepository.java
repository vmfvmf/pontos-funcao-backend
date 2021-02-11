package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Produto;

@Repository("produtoRepository")
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	 Produto findByNome(String nome);
}
