package com.springboot.payment_gateway_demo.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private Double price;
    private Integer stock;
    private String description;
    private String name;
}
