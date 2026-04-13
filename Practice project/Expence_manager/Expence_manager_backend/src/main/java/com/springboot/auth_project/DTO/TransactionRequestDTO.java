package com.springboot.auth_project.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequestDTO {

    private String title;
    private Double amount;
    private String description;
    private String categoryName;
    private String type;
    private LocalDate transactionDate;
}
