package com.nexushr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexushr.entity.Performance;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {

}