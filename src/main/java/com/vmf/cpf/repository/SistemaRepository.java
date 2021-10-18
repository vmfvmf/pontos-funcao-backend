package com.vmf.cpf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.cpf.entities.Sistema;

@Repository("sistemaRepository")
public interface SistemaRepository extends JpaRepository<Sistema, Long> {
}
