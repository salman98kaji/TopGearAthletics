package com.blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.dto.UserLoginDTO;
import com.blogs.dto.UserRegistrationDTO;
import com.blogs.dto.UserResponseDTO;
import com.blogs.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Registration endpoint
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
		try {
			UserResponseDTO registeredUser = userService.registerUser(userRegistrationDTO);
			return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//Login Endpoint
	@PostMapping("/login")
	public ResponseEntity<UserResponseDTO> loginUser (@RequestBody UserLoginDTO userLoginDTO){
		return userService.loginUser(userLoginDTO)
				.map(UserResponseDTO -> new ResponseEntity<>(UserResponseDTO, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
	}
	
}
