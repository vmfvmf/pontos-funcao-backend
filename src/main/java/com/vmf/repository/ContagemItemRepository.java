package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.entities.AbstractContagemItem;


@Repository("contagemItemRepository")
public interface ContagemItemRepository extends JpaRepository<AbstractContagemItem, Long> {
}