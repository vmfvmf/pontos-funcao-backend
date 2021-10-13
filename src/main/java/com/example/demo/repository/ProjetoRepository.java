package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Projeto;

@Repository("projetoRepository")
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
