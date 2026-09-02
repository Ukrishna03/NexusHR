package com.nexushr.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordDTO {

	 public String token;
	 public String newPassword;
}
