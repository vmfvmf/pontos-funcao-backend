package com.vmf.cpf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.cpf.entities.Contagem;

@Repository("contagemRepository")
public interface ContagemRepository extends JpaRepository<Contagem, Long> {
}
