package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tabela;


@Repository("tabelaRepository")
public interface TabelaRepository extends JpaRepository<Tabela, Long> {
}
