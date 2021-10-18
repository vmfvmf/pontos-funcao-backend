package com.vmf.cpf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.cpf.entities.Projeto;

@Repository("projetoRepository")
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
