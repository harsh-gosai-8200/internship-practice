package com.springboot.auth_project.service;

import com.springboot.auth_project.DTO.ProfileRequest;
import com.springboot.auth_project.DTO.ProfileResponse;

public interface ProfileService {

    ProfileResponse createProfile(ProfileRequest request);

    ProfileResponse getProfile(String email);

    void sendResetOtp(String email);

    void resetPassword(String email, String otp, String newPassword);

    void sendOtp(String email);

    void verifyOtp(String email, String otp);
}
