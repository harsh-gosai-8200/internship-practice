package com.springboot.payment_gateway_demo.controller;

import com.springboot.payment_gateway_demo.DTO.ProductRequest;
import com.springboot.payment_gateway_demo.DTO.ProductResponse;
import com.springboot.payment_gateway_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request){
        return productService.create(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @GetMapping
    public List<ProductResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Boolean inStock){
        return productService.search(name, price, inStock);
    }
}
