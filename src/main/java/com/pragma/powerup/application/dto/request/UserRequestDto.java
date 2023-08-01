package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private Long id;
    private String name;
    private String lastName;
    private String idNumber;
    private String phone;
    private String email;
    private String password;
    private RolRequestDto rolId;
}