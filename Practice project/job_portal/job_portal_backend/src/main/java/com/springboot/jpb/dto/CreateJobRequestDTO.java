package com.springboot.jpb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateJobRequestDTO {

    @NotNull
    private String title;

    private String description;

    private String location;

    @NotNull
    private Double salary;

    private Long userId;
}
