package fi.haagahelia.coolreads.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterUserDto {
	@NotBlank(message = "Username is required")
	private String username;
	@NotBlank(message = "Password is required")
	private String password;

	public RegisterUserDto() {
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

}