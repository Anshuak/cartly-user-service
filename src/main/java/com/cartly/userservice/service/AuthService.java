package com.cartly.userservice.service;

import com.cartly.userservice.dto.UserRequestDto;
import com.cartly.userservice.dto.UserResponseDto;
import com.cartly.userservice.entity.User;
import com.cartly.userservice.repository.UserRepository;
import com.cartly.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final ModelMapper modelMapper;


    public String generateToken(String username){
        return jwtUtil.generateToken(username);
    }

    public void validateToken(String token){
        jwtUtil.validateToken(token);
    }

    public UserResponseDto signup(UserRequestDto userRequestDto){
        // convert dto to entity
        User user = modelMapper.map(userRequestDto, User.class);
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // save user
        User savedUser= userRepository.save(user);
        // convert entity to dto
        return modelMapper.map(savedUser, UserResponseDto.class);
    }


}
