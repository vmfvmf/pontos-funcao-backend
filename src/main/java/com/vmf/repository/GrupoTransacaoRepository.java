package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.entities.Grupo;



@Repository("grupoTransacaoRepository")
public interface GrupoTransacaoRepository extends JpaRepository<Grupo, Long> {
}