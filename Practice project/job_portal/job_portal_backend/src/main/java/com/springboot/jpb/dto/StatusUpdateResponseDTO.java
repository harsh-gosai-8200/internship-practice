package com.springboot.jpb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusUpdateResponseDTO {

    private Long applicationId;
    private String status;
}
