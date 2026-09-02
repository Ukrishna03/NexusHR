package com.nexushr.DTO;

import com.nexushr.Enum.Role;

import lombok.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class RegisterRequest {
	public String userName;
	public String userEmail;
	public String password;
	public Role role;
	

}
