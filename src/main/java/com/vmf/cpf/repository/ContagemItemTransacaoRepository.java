package com.vmf.cpf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.cpf.entities.ContagemItemTransacao;


@Repository("contagemItemTransacaoRepository")
public interface ContagemItemTransacaoRepository extends JpaRepository<ContagemItemTransacao, Long> {
}