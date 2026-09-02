package com.nexushr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nexushr.DTO.EmailLogDTO;
import com.nexushr.entity.EmailLog;
import com.nexushr.repository.EmailLogRepository;

import jakarta.mail.internet.MimeMessage;


@Service

public class EmailLogService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private EmailLogRepository emailLogRepo;
	
	public void sendPasswordResetEmail(String to, String token) {
		String passwordResetLink ="http://localhost:6161/auth/reset-password?token=" + token;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Reset your password");
		message.setText("Click the link to reset your password:\n"+passwordResetLink);
		
		javaMailSender.send(message);
	
	}
	
	public String getNotifictaion(EmailLogDTO emailLogs) {
		
		boolean sentStatus=false;
		
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			
			helper.setTo(emailLogs.recipientEmail);
			helper.setSubject(emailLogs.subject);
			helper.setText(emailLogs.body,true);
			
			javaMailSender.send(message);
			sentStatus=true;
			
		}catch (Exception e) {
			// TODO: handle exception
			sentStatus=false;
			throw new RuntimeException("Email not found");
		}
		EmailLog log = new EmailLog(
			    emailLogs.recipientEmail,
			    emailLogs.subject,
			    emailLogs.body,
			    sentStatus
			);
		emailLogRepo.save(log);
		
		return sentStatus?"Email sent Successfully":"Email sending Failed";
	}

}
