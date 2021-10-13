package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AbstractContagemItem;


@Repository("contagemItemRepository")
public interface ContagemItemRepository extends JpaRepository<AbstractContagemItem, Long> {
}