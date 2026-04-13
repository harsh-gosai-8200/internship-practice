package com.springboot.payment_gateway_demo.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer quantity;
}
