package com.nexushr.DTO;

import lombok.*;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginRequest {

	public String userEmail;
	public String password;
}
