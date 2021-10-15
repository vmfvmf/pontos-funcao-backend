package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.entities.ContagemItemArquivoReferenciado;


@Repository("contagemItemArquivoReferenciadoRepository")
public interface ContagemItemArquivoReferenciadoRepository extends JpaRepository<ContagemItemArquivoReferenciado, Long> {
}