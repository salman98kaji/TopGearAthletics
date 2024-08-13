package com.blogs.service;

import com.blogs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dto.UserLoginDTO;
import com.blogs.dto.UserRegistrationDTO;
import com.blogs.dto.UserResponseDTO;
import com.blogs.entities.User;
import com.blogs.entities.Enums.UserRole;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    
    //private BCryptPassword passwordEncoder;

	@Override
	public UserResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
		if(userRepository.existsByUsername(userRegistrationDTO.getUsername())) {
			throw new RuntimeException("Username is already takn");
		}
		if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }
		
		User user = new User();
		user.setUsername(userRegistrationDTO.getUsername());
		user.setPassword(userRegistrationDTO.getPassword());
		user.setEmail(userRegistrationDTO.getEmail());
		user.setRole(UserRole.valueOf(userRegistrationDTO.getRole()));
		
		User savedUser = userRepository.save(user);
		
		UserResponseDTO responseDTO = new UserResponseDTO();
		responseDTO.setUserId(savedUser.getUserId());
		responseDTO.setUsername(savedUser.getUsername());
		responseDTO.setEmail(savedUser.getEmail());
		responseDTO.setRole(savedUser.getRole().name());
		
		return responseDTO;
	}

	@Override
	public Optional<UserResponseDTO> loginUser(UserLoginDTO userLoginDTO) {
		User user = userRepository.findByUsername(userLoginDTO.getUsername());
		if(user != null && userLoginDTO.getPassword().equals(user.getPassword())) {
			UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setUserId(user.getUserId());
            responseDTO.setUsername(user.getUsername());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setRole(user.getRole().name());
            
            return Optional.of(responseDTO);
		}
		return Optional.empty();
	}

    
}

