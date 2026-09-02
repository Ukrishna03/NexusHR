package com.nexushr.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexushr.DTO.AuthResponse;
import com.nexushr.DTO.ForgotPasswordDTO;
import com.nexushr.DTO.LoginRequest;
import com.nexushr.DTO.RegisterRequest;
import com.nexushr.DTO.ResetPasswordDTO;
import com.nexushr.entity.UserAuth;
import com.nexushr.repository.UserAuthRepository;
import com.nexushr.security.EmailLogService;
import com.nexushr.security.JWTUtil;
import com.nexushr.security.TokenBlockService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserAuthService {
	@Autowired
	private UserAuthRepository userRepo;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private EmailLogService emailService;
	
	@Autowired
	private TokenBlockService tokenBlockService;
	
	public AuthResponse register(RegisterRequest register) {
		Optional<UserAuth> existing = userRepo.findByUserEmail(register.userEmail);
		if (existing.isPresent()) {
			throw new RuntimeException("User already exist"); 
		}
		
		UserAuth user = new UserAuth();
		user.setUserName(register.userName);
		user.setUserEmail(register.userEmail);
		user.setPassword(passwordEncoder.encode(register.password));
		user.setRole(register.role);
		
		userRepo.save(user);
		
		String token = jwtUtil.generatedToken(user);
		return new AuthResponse(token,"User register successfully");
	}
	
	public String login(LoginRequest login) {
		
		UserAuth user = userRepo.findByUserEmail(login.userEmail)
				.orElseThrow(()-> new  RuntimeException("User not Found"));
		
		if (!passwordEncoder.matches(login.password, user.getPassword())) {
			throw new RuntimeException("invalid Credential");
		}
		return jwtUtil.generatedToken(user);
		
	}
	public void forgotPassword(ForgotPasswordDTO forgotPassword) {
		UserAuth user = userRepo.findByUserEmail(forgotPassword.userEmail).orElseThrow(()->new RuntimeException("User not found"));
		
		String token = UUID.randomUUID().toString();
		user.setResetToken(token);
		user.setResetTokenExpiry(new Date(System.currentTimeMillis()+10*60*1000L));
		userRepo.save(user);
		emailService.sendPasswordResetEmail(forgotPassword.userEmail, token);
		
	}
	
	public void resetPassword(ResetPasswordDTO resetPassword) {

		UserAuth user = userRepo.findByResetToken(resetPassword.token)
		        .orElseThrow(() -> new RuntimeException("Invalid Token"));

	    if (user.getResetTokenExpiry().before(new Date())) {
	        throw new RuntimeException("Token got expired");
	    }

	    user.setPassword(passwordEncoder.encode(resetPassword.newPassword));
	    user.setResetToken(null);
	    user.setResetTokenExpiry(null);

	    userRepo.save(user);
	}
	
	
	public String logout(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = jwtUtil.extractToken(header);
		if(token != null) {
			tokenBlockService.blockToken(token);
			
		}
		return "Logged out successful";
	}
	
}
