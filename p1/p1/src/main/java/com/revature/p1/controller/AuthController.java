package com.revature.p1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.p1.dto.request.LoginRequest;
import com.revature.p1.dto.request.SignUpRequest;
import com.revature.p1.dto.response.JwtAuthResponse;
import com.revature.p1.dto.response.UserDto;
import com.revature.p1.service.AuthService;
import com.revature.p1.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    UserService userService;
    AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody SignUpRequest user){
         
        return ResponseEntity.ok(new UserDto(userService.registerUser(user)));       
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginRequest user)  {
         
        String token = authService.login(user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);    
    }
}
