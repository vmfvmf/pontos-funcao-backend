package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.entities.Projeto;

@Repository("projetoRepository")
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
