package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MensagemTela;


@Repository("mensagemTelatRepository")
public interface MensagemTelaRepository extends JpaRepository<MensagemTela, Long> {
}
