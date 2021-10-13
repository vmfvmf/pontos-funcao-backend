package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.model.Contagem;

@Repository("contagemRepository")
public interface ContagemRepository extends JpaRepository<Contagem, Long> {
}
