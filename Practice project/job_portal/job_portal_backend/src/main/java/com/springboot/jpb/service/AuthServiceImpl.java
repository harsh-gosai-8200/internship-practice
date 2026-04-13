package com.springboot.jpb.service;

import com.springboot.jpb.dto.LoginRequestDTO;
import com.springboot.jpb.dto.LoginResponseDTO;
import com.springboot.jpb.dto.RegisterRequestDTO;
import com.springboot.jpb.dto.RegistrationResponseDTO;
import com.springboot.jpb.entity.User;
import com.springboot.jpb.entity.enums.Roles;
import com.springboot.jpb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegistrationResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());

        Roles role;
        try {
            role = Roles.valueOf(registerRequestDTO.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role");
        }

        User user = User.builder()
                .email(registerRequestDTO.getEmail())
                .password(encodedPassword)
                .name(registerRequestDTO.getName())
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return RegistrationResponseDTO.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Password");
        }

        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .email(user.getEmail())
                .token(token)
                .userId(user.getId())
                .build();
    }
}
