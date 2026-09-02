package com.nexushr.entity;

import java.util.Date;

import com.nexushr.Enum.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_auth")

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserAuth {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String userName;
	@Column(unique=true,nullable=false)
	private String userEmail;
	@Column(nullable=false)
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String resetToken;
	private Date resetTokenExpiry;;
	
//	public UserAuth() {}
//	public UserAuth(Long id, String username, Role role) {
//		this.id=id;
//		this.userName=userName;
//		this.userEmail=userEmail;
//		this.password=passsword;
//		this.role=role;
//		
//	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getResetToken() {
	    return resetToken;
	}

	public void setResetToken(String resetToken) {
	    this.resetToken = resetToken;
	}

	public Date getResetTokenExpiry() {
	    return resetTokenExpiry;
	}

	public void setResetTokenExpiry(Date resetTokenExpiry) {
	    this.resetTokenExpiry = resetTokenExpiry;
	}
	
	

}
