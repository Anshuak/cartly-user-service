package com.cartly.userservice.controller;

import com.cartly.userservice.dto.LoginRequestDto;
import com.cartly.userservice.dto.SignupResponseDto;
import com.cartly.userservice.dto.UserRequestDto;
import com.cartly.userservice.dto.UserResponseDto;
import com.cartly.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = authService.signup(userRequestDto);
        SignupResponseDto response = new SignupResponseDto("User created successfully", 201, userResponseDto);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody LoginRequestDto loginRequestDto) {
       Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
       if(authentication.isAuthenticated()){
           return authService.generateToken(loginRequestDto.getEmail());
       }
       else throw new RuntimeException("Invalid credentials");
    }
}
