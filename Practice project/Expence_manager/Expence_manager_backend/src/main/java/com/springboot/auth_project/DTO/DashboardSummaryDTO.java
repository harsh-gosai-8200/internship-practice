package com.springboot.auth_project.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardSummaryDTO {

    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
}