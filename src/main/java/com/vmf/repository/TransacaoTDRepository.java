package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.model.TransacaoTD;

@Repository("transacaoTDRepository")
public interface TransacaoTDRepository extends JpaRepository<TransacaoTD, Long> {
}