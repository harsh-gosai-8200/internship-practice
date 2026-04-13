package com.springboot.jpb.controller;

import com.springboot.jpb.dto.LoginRequestDTO;
import com.springboot.jpb.dto.LoginResponseDTO;
import com.springboot.jpb.dto.RegisterRequestDTO;
import com.springboot.jpb.dto.RegistrationResponseDTO;
import com.springboot.jpb.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        RegistrationResponseDTO responseDTO = authService.register(registerRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
}
