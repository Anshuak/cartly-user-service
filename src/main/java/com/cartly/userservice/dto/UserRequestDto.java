package com.cartly.userservice.dto;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
}
