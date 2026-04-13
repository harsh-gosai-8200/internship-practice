package com.springboot.auth_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {

    private Long id;

    private String title;

    private Double amount;

    private String description;

    private String type;

    private String categoryName;

    private LocalDate transactionDate;
}
