package com.vmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmf.entities.Sprint;

@Repository("sprintRepository")
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
