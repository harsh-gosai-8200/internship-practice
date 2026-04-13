package com.springboot.jpb.service;

import com.springboot.jpb.entity.User;

public interface JwtService {

    String generateToken(User user);

    String extractEmail(String token);

    boolean isTokenValid(String token, User user);
}
