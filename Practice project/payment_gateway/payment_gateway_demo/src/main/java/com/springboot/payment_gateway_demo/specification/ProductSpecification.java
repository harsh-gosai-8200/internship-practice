package com.springboot.payment_gateway_demo.specification;

import com.springboot.payment_gateway_demo.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasName(String name){
        return ((root, query, criteriaBuilder) ->
                name == null ? null :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
    }

    public static Specification<Product> priceGreaterThan(Double price){
        return ((root, query, criteriaBuilder) ->
                price == null ? null :
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> inStock(Boolean stock){
        return (root, query, cb) -> {
            if (stock == null) return null;
            return stock
                    ? cb.greaterThan(root.get("stock"), 0)
                    : cb.equal(root.get("stock"), 0);
        };
    }
}
