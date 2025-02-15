package com.cartly.userservice.service;

import com.cartly.userservice.config.CustomUserDetails;
import com.cartly.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUsersDetailService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<com.cartly.userservice.entity.User> user = userRepository.findByEmail(email);
        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found "+ email));
    }
}
