package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sistema;

@Repository("sistemaRepository")
public interface SistemaRepository extends JpaRepository<Sistema, Long> {
}
