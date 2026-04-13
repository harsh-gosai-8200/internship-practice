package com.springboot.auth_project.specification;

import com.springboot.auth_project.DTO.TransactionFilterDTO;
import com.springboot.auth_project.entity.TransactionEntity;
import com.springboot.auth_project.entity.TransactionType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {

    public static Specification<TransactionEntity> filterTransactions(TransactionFilterDTO filter) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter.getType() != null){
                predicates.add(
                        criteriaBuilder.equal(root.get("type"), TransactionType.valueOf(filter.getType()))
                );
            }

            if(filter.getCategoryName() != null){
                predicates.add(
                        criteriaBuilder.equal(root.get("category").get("id"), filter.getCategoryName())
                );
            }

            if(filter.getMinAmount() != null){
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), filter.getMinAmount())
                );
            }

            if(filter.getMaxAmount() != null){
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("amount"), filter.getMaxAmount())
                );
            }

            if(filter.getStartDate() != null){
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("transactionDate"), filter.getStartDate())
                );
            }

            if(filter.getEndDate() != null){
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("transactionDate"), filter.getEndDate())
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
