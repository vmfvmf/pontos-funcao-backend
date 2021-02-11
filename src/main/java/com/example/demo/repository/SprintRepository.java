package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sprint;


@Repository("sprintRepository")
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
