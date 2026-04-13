package com.springboot.jpb.service;

import com.springboot.jpb.dto.LoginRequestDTO;
import com.springboot.jpb.dto.LoginResponseDTO;
import com.springboot.jpb.dto.RegisterRequestDTO;
import com.springboot.jpb.dto.RegistrationResponseDTO;

public interface AuthService {

    RegistrationResponseDTO register(RegisterRequestDTO registerRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
