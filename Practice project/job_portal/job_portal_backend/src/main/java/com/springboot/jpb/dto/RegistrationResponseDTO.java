package com.springboot.jpb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponseDTO {

    private Long id;
    private String email;
}
