package com.nexushr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexushr.entity.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long>{

}
