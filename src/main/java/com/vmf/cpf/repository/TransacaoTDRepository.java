package com.vmf.cpf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.cpf.entities.TransacaoTD;

@Repository("transacaoTDRepository")
public interface TransacaoTDRepository extends JpaRepository<TransacaoTD, Long> {
}
