package com.springboot.jpb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobApplicationsResponseDTO {

    private Long applicationId;
    private String candidateName;
    private String candidateEmail;
    private String status;
}
