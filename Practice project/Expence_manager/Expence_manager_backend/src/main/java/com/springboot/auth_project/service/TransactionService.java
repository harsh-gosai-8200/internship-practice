package com.springboot.auth_project.service;

import com.springboot.auth_project.DTO.DashboardSummaryDTO;
import com.springboot.auth_project.DTO.TransactionFilterDTO;
import com.springboot.auth_project.DTO.TransactionRequestDTO;
import com.springboot.auth_project.DTO.TransactionResponseDTO;
import com.springboot.auth_project.entity.UserEntity;

import java.util.List;

public interface TransactionService {

    TransactionResponseDTO addTransaction(TransactionRequestDTO transactionRequestDTO, UserEntity user);

    TransactionResponseDTO updateTransaction(Long transactionId, TransactionRequestDTO transactionRequestDTO, UserEntity user);

    void deleteTransaction(Long transactionId, UserEntity user);

    List<TransactionResponseDTO> getAllTransactions(TransactionFilterDTO filterDTO, UserEntity user);

    DashboardSummaryDTO getDashboardSummary(UserEntity user);
}
