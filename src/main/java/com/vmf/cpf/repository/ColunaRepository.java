package com.vmf.cpf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.cpf.entities.Coluna;


@Repository("colunaRepository")
public interface ColunaRepository extends JpaRepository<Coluna, Long> {
}