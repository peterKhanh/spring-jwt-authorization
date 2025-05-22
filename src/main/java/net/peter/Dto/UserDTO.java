package net.peter.Dto;

import java.util.Set;

public class UserDTO {
	private String email;
	private String password;
	private Set<String> role;
	public UserDTO(String email, String password, Set<String> role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public UserDTO() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}


}
