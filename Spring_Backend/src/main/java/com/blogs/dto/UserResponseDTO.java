package com.blogs.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
	private Long userId;
    private String username;
    private String email;
    private String role;
}
