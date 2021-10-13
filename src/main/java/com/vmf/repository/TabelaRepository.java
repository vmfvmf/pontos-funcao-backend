package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.model.Tabela;

@Repository("tabelaRepository")
public interface TabelaRepository extends JpaRepository<Tabela, Long> {
}
