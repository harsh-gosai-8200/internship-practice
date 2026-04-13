package com.springboot.auth_project.service;

import com.springboot.auth_project.DTO.DashboardSummaryDTO;
import com.springboot.auth_project.DTO.TransactionFilterDTO;
import com.springboot.auth_project.DTO.TransactionRequestDTO;
import com.springboot.auth_project.DTO.TransactionResponseDTO;
import com.springboot.auth_project.entity.CategoryEntity;
import com.springboot.auth_project.entity.TransactionEntity;
import com.springboot.auth_project.entity.TransactionType;
import com.springboot.auth_project.entity.UserEntity;
import com.springboot.auth_project.repository.CategoryRepository;
import com.springboot.auth_project.repository.TransactionRepository;
import com.springboot.auth_project.repository.UserRepository;
import com.springboot.auth_project.specification.TransactionSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    /**
     * Add transaction (expense/income)
     * @param transactionRequestDTO
     * @return
     */
    @Override
    @Transactional
    public TransactionResponseDTO addTransaction(TransactionRequestDTO transactionRequestDTO, UserEntity user) {

//        UserEntity user = userRepository.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));

        CategoryEntity category = categoryRepository.findByName(transactionRequestDTO.getCategoryName())
                .orElseGet(() -> {
                    CategoryEntity newCategory = new CategoryEntity();
                    newCategory.setName(transactionRequestDTO.getCategoryName());
                    return categoryRepository.save(newCategory);
                });

        TransactionEntity transaction = modelMapper.map(transactionRequestDTO, TransactionEntity.class);
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setType(TransactionType.valueOf(transactionRequestDTO.getType().toUpperCase()));

        TransactionEntity saved = transactionRepository.save(transaction);
        return convertToDTO(saved);

    }

    /**
     * Update transaction
     * @param transactionId
     * @param transactionRequestDTO
     * @return
     */
    @Override
    @Transactional
    public TransactionResponseDTO updateTransaction(Long transactionId, TransactionRequestDTO transactionRequestDTO, UserEntity user) {

        TransactionEntity transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if(!transaction.getUser().getId().equals(user.getId())){
            throw new RuntimeException("User not found");
        }

        CategoryEntity category = categoryRepository.findByName(transactionRequestDTO.getCategoryName())
                .orElseGet(() -> {
                    // Optional: Create category if it doesn't exist
                    CategoryEntity newCategory = new CategoryEntity();
                    newCategory.setName(transactionRequestDTO.getCategoryName());
                    return categoryRepository.save(newCategory);
                });

        transaction.setTitle(transactionRequestDTO.getTitle());
        transaction.setAmount(transactionRequestDTO.getAmount());
        transaction.setDescription(transactionRequestDTO.getDescription());
        transaction.setTransactionDate(transactionRequestDTO.getTransactionDate());
        transaction.setType(TransactionType.valueOf(transactionRequestDTO.getType()));
        transaction.setCategory(category);

        TransactionEntity transactionEntity = transactionRepository.save(transaction);

        return convertToDTO(transactionEntity);
    }

    /**
     * Delete transaction
     * @param transactionId
     */
    @Override
    @Transactional
    public void deleteTransaction(Long transactionId, UserEntity user) {

        TransactionEntity transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if(!transaction.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized access");
        }

        transactionRepository.delete(transaction);
    }

    /**
     * Get all the transactions
     * @param filterDTO
     * @return
     */
    @Override
    public List<TransactionResponseDTO> getAllTransactions(TransactionFilterDTO filterDTO, UserEntity user) {

        Specification<TransactionEntity> spec = TransactionSpecification.filterTransactions(filterDTO)
                .and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), user.getId()));

        List<TransactionEntity> transactionEntities = transactionRepository.findAll(spec);

        return transactionEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    /**
     * Get all the statistics for dashboard
     * @return
     */
    @Override
    public DashboardSummaryDTO getDashboardSummary(UserEntity user) {

        List<TransactionEntity> transactionEntities = transactionRepository.findAll(
                (root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("user").get("id"), user.getId()));

        double totalIncome = transactionEntities.stream()
                .filter(t -> t.getType()==TransactionType.INCOME)
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        double totalExpense = transactionEntities.stream()
                .filter(t -> t.getType()==TransactionType.EXPENSE)
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        double balance = totalIncome-totalExpense;

        return DashboardSummaryDTO.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .balance(balance)
                .build();
    }

    /**
     * Helper method to convert entity to DTO
     * @param transactionEntity
     * @return
     */
    private TransactionResponseDTO convertToDTO(TransactionEntity transactionEntity) {
        TransactionResponseDTO responseDTO = modelMapper.map(transactionEntity, TransactionResponseDTO.class);
        responseDTO.setCategoryName(transactionEntity.getCategory().getName());
        responseDTO.setType(transactionEntity.getType().name());

        return responseDTO;
    }
}
