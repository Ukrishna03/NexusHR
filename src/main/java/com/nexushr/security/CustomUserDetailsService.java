package com.nexushr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nexushr.entity.UserAuth;
import com.nexushr.repository.UserAuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {

        UserAuth user = userRepo.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not Found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUserEmail(),
                user.getPassword(),
                java.util.Collections.emptyList());
    }
}