package fi.haagahelia.coolreads.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterUserDto {

	@NotEmpty(message = "Username cannot be empty")
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
	private String username;

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;

	@NotEmpty(message = "Role cannot be empty")
	private String role;

	public RegisterUserDto() {
	}

	public RegisterUserDto(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
