package com.springboot.payment_gateway_demo.service;

import com.springboot.payment_gateway_demo.DTO.ProductRequest;
import com.springboot.payment_gateway_demo.DTO.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest productRequest);

    ProductResponse getById(Long id);

    List<ProductResponse> search(String name, Double minPrice, Boolean inStock);
}
