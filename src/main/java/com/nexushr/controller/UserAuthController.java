package com.nexushr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexushr.DTO.AuthResponse;
import com.nexushr.DTO.ForgotPasswordDTO;
import com.nexushr.DTO.LoginRequest;
import com.nexushr.DTO.RegisterRequest;
import com.nexushr.DTO.ResetPasswordDTO;
import com.nexushr.service.UserAuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user_Auth")
@RequiredArgsConstructor

public class UserAuthController {
	
	@Autowired
	private UserAuthService userAuthServices;
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse>register(@RequestBody RegisterRequest register){
		return ResponseEntity.ok(userAuthServices.register(register));
		
	}
	
	@PostMapping("/Login")
	public ResponseEntity<String> Login(@RequestBody LoginRequest login) {

	    String token = userAuthServices.login(login);

	    return ResponseEntity.ok(token);
	}
	@PostMapping("/forgot_password")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDTO forgotPassword){
		userAuthServices.forgotPassword(forgotPassword);
		return ResponseEntity.ok("Reset password link send over on your Email");
	}
	@PostMapping("/reset_password")
	public ResponseEntity<String>resetPassword(@RequestBody ResetPasswordDTO resetPassword){
		userAuthServices.resetPassword(resetPassword);
		return ResponseEntity.ok("Reset password successfully");
	}
	@PostMapping("/logout")
	public ResponseEntity<String>logout(HttpServletRequest request){
		return ResponseEntity.ok(userAuthServices.logout(request));
		
	}
}
