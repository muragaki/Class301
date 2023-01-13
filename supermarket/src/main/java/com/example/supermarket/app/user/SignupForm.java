package com.example.supermarket.app.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupForm {
	@Size(min=8, max=16)
	private String username;
	@Size(min=8, max=16)
	private String password;
	@Size(min=1, max=8)
	private String firstname;
	@Size(min=1, max=8)
	private String lastname;
}
