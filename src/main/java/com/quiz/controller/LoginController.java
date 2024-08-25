package com.quiz.controller;

import com.quiz.dto.LoginDto;
import com.quiz.dto.LoginResponseDto;
import com.quiz.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public LoginController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
        } catch (BadCredentialsException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        String jwtToken = JwtUtils.generateToken(userDetails);
        long expiresIn = JwtUtils.getExpirationTime();
        LoginResponseDto loginRes = new LoginResponseDto(jwtToken, expiresIn);
        return ResponseEntity.ok(loginRes);
    }
}
