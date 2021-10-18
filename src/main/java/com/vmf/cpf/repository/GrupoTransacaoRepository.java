package com.vmf.cpf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.cpf.entities.Grupo;



@Repository("grupoTransacaoRepository")
public interface GrupoTransacaoRepository extends JpaRepository<Grupo, Long> {
}