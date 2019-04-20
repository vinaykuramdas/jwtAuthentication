package com.security.Jwttokendemo.model;

public class JwtUser {
	
	private String userName;
	private long id;
	private String role;

	

	public void setId(long id) {
		this.id = id;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
		
	}

}
