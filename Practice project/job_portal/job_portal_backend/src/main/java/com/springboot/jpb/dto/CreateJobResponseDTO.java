package com.springboot.jpb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateJobResponseDTO {

    private Long jobId;
    private String title;
    private String description;
    private String location;
    private Double salary;
    private Long createdBy;
}
