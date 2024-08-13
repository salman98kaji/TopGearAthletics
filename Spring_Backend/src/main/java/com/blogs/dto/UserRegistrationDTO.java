package com.blogs.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
	private String username;
    private String password;
    private String email;
    private String role;
}
