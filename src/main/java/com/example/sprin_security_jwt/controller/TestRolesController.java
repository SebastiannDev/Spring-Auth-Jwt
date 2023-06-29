package com.example.sprin_security_jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {
    
    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    String accessMain(){
        return "Este es el rol ADMIN";
    }

    @GetMapping("/accessUser")
    @PreAuthorize("hasRole('USER') or hasRole('INVITED')")
    String accesUser(){
        return "Este es el rol USER";
    }

    @GetMapping("/accessInvited")
    @PreAuthorize("hasAnyRole('USER', 'INVITED')")
    String accesInvited(){
        return "Este es el rol INVITED";
    }
}
