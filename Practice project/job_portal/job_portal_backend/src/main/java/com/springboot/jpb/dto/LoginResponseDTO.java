package com.springboot.jpb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {

    public String token;
    public Long userId;
    public String email;
}
