package com.nexushr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexushr.entity.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findByUserEmail(String userEmail);
    Optional<UserAuth> findByResetToken(String resetToken);
}