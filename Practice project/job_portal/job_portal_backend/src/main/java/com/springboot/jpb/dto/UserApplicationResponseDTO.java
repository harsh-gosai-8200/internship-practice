package com.springboot.jpb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserApplicationResponseDTO {
    private Long applicationId;
    private String jobTitle;
    private String jobLocation;
    private Double salary;
    private String status;
}
