package com.springboot.auth_project.controller;

import com.springboot.auth_project.DTO.DashboardSummaryDTO;
import com.springboot.auth_project.DTO.TransactionFilterDTO;
import com.springboot.auth_project.DTO.TransactionRequestDTO;
import com.springboot.auth_project.DTO.TransactionResponseDTO;
import com.springboot.auth_project.entity.UserEntity;
import com.springboot.auth_project.service.AppUserDetailsService;
import com.springboot.auth_project.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final AppUserDetailsService appUserDetailsService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> addTransaction(
            @RequestBody TransactionRequestDTO transactionRequestDTO,
            @AuthenticationPrincipal User userDetails) {

        UserEntity user = appUserDetailsService.loadUserEntityByEmail(userDetails.getUsername());
        TransactionResponseDTO responseDTO = transactionService.addTransaction(transactionRequestDTO, user);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(
            @PathVariable Long id,
            @RequestBody TransactionRequestDTO transactionRequestDTO,
            @AuthenticationPrincipal User userDetails){

        UserEntity user = appUserDetailsService.loadUserEntityByEmail(userDetails.getUsername());
        TransactionResponseDTO responseDTO = transactionService.updateTransaction(id, transactionRequestDTO, user);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(
            @PathVariable Long id,
            @AuthenticationPrincipal User userDetails){

        UserEntity user = appUserDetailsService.loadUserEntityByEmail(userDetails.getUsername());
        transactionService.deleteTransaction(id, user);

        return ResponseEntity.ok("Transaction deleted deleted Successfully");
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long categoryName,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @AuthenticationPrincipal User userDetails){

        TransactionFilterDTO filterDTO = new TransactionFilterDTO();
        filterDTO.setType(type);
        filterDTO.setCategoryName(categoryName);
        filterDTO.setMinAmount(minAmount);
        filterDTO.setMaxAmount(maxAmount);

        if(startDate != null) filterDTO.setStartDate(java.time.LocalDate.parse(startDate));
        if(endDate != null) filterDTO.setEndDate(java.time.LocalDate.parse(endDate));

        UserEntity user = appUserDetailsService.loadUserEntityByEmail(userDetails.getUsername());
        List<TransactionResponseDTO> transaction = transactionService.getAllTransactions(filterDTO, user);

        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardSummaryDTO> dashboardSummary(@AuthenticationPrincipal User userDetails){

        UserEntity user = appUserDetailsService.loadUserEntityByEmail(userDetails.getUsername());
        DashboardSummaryDTO dashboardSummaryDTO = transactionService.getDashboardSummary(user);

        return ResponseEntity.ok(dashboardSummaryDTO);
    }

    @GetMapping("/test")
    public String test(){
        return "Transaction API working";
    }
}
