package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ded;

@Repository("dedRepository")
public interface DedRepository extends JpaRepository<Ded, Long> {
}
