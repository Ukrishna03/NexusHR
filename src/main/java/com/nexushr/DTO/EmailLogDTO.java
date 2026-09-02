package com.nexushr.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmailLogDTO {
	public String recipientEmail;
	public String subject;
	public String body;
	
	

}
