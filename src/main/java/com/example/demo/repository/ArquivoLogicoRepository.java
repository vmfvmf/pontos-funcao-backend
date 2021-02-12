package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ArquivoLogico;


@Repository("arquivoLogicotRepository")
public interface ArquivoLogicoRepository extends JpaRepository<ArquivoLogico, Long> {
}
