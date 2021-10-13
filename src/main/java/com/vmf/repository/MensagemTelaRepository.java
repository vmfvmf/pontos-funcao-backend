package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.model.MensagemTela;


@Repository("mensagemTelatRepository")
public interface MensagemTelaRepository extends JpaRepository<MensagemTela, Long> {
}
