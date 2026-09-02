package com.nexushr.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="email_Logs")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class EmailLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String recepientEmail;
	private String subject;
	
	@Column (length=10000)
	private String body;
	private Boolean sentStatus;
	private LocalDateTime sentAt = LocalDateTime.now();
	
	public EmailLog(String recipientEmail, String subject, String body, boolean sentStatus) {
		this.recepientEmail = recipientEmail;
		this.subject = subject ;
		this.body=body;
		this.sentStatus=sentStatus;
	}
}
