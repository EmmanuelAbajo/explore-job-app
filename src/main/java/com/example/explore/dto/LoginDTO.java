package com.example.explore.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {

	@NotNull
	private String username;

	@NotNull
	private String password;

	private String firstName;

	private String lastName;
	
	protected LoginDTO() {}

	public LoginDTO(@NotNull String username, @NotNull String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	};
	
	

}
