package com.nexushr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexushr.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

}