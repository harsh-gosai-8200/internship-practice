package com.springboot.payment_gateway_demo.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponse {
    private String sessionId;
    private List<CartItemResponse> items;
}
