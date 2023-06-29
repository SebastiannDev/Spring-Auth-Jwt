package com.example.sprin_security_jwt.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @Email @NotBlank String email,
        @NotBlank String username,
        @NotBlank String password,
        Set<String> roles) {

}
