package com.springboot.jpb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobResponseDTO {

    private Long jobId;
    private String title;
    private String description;
    private Double salary;
    private String location;
    private Long applicationCount;

}
