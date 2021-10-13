package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.model.ContagemItemTransacao;


@Repository("contagemItemTransacaoRepository")
public interface ContagemItemTransacaoRepository extends JpaRepository<ContagemItemTransacao, Long> {
}