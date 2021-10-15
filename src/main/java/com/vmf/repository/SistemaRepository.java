package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.entities.Sistema;

@Repository("sistemaRepository")
public interface SistemaRepository extends JpaRepository<Sistema, Long> {
}
