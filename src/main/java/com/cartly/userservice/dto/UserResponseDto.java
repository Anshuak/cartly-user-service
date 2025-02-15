package com.cartly.userservice.dto;

import com.cartly.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private User.Role role = User.Role.CUSTOMER;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
