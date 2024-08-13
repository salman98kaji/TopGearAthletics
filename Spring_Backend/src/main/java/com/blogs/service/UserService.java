package com.blogs.service;

import java.util.Optional;

import com.blogs.dto.UserLoginDTO;
import com.blogs.dto.UserRegistrationDTO;
import com.blogs.dto.UserResponseDTO;

public interface UserService {
	UserResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO);
    Optional<UserResponseDTO> loginUser(UserLoginDTO userLoginDTO);
}
