package com.vmf.cpf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.cpf.entities.ContagemItemArquivoReferenciado;


@Repository("contagemItemArquivoReferenciadoRepository")
public interface ContagemItemArquivoReferenciadoRepository extends JpaRepository<ContagemItemArquivoReferenciado, Long> {
}