package com.example.sprin_security_jwt.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprin_security_jwt.dto.CreateUserDto;
import com.example.sprin_security_jwt.models.Erole;
import com.example.sprin_security_jwt.models.RoleEntity;
import com.example.sprin_security_jwt.models.UserEntity;
import com.example.sprin_security_jwt.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/welcome")
    String hello() {
        return "Hello world not secured";
    }

    @GetMapping("/welcomeSafe")
    String helloSafe() {
        return "Hello world secured";
    }

    @PostMapping("/createUser")
    ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto userDto) {

        var roles = userDto.roles()
                .stream()
                .map(role -> RoleEntity.builder()
                        .name(Erole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        var userEntity = UserEntity.builder()
                .email(userDto.email())
                .username(userDto.username())
                .password(passwordEncoder.encode(userDto.password()))
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    String deleteUser(@RequestParam Long id) {
        userRepository.deleteById(id);
        return "Se ha eliminado el usuario: " + id;
    }
}
