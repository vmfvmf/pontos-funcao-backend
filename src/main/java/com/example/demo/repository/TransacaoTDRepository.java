package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TransacaoTD;


@Repository("transacaoTDRepository")
public interface TransacaoTDRepository extends JpaRepository<TransacaoTD, Long> {
}
