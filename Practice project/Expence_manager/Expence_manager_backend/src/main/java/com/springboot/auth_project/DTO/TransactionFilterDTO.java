package com.springboot.auth_project.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionFilterDTO {

    private String type;

    private Long categoryName;

    private Double minAmount;

    private Double maxAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private String keyword;
}
