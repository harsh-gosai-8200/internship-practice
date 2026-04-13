package com.springboot.payment_gateway_demo.service;

import com.springboot.payment_gateway_demo.DTO.ProductRequest;
import com.springboot.payment_gateway_demo.DTO.ProductResponse;
import com.springboot.payment_gateway_demo.Exception.BadRequestException;
import com.springboot.payment_gateway_demo.Exception.ResourceNotFoundException;
import com.springboot.payment_gateway_demo.model.Product;
import com.springboot.payment_gateway_demo.repository.ProductRepository;
import com.springboot.payment_gateway_demo.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest productRequest) {

        if (productRequest.getName() == null || productRequest.getName().isBlank()) {
            throw new BadRequestException("Product name is required");
        }

        if (productRequest.getPrice() == null || productRequest.getPrice() <= 0) {
            throw new BadRequestException("Price must be greater than 0");
        }

        if (productRequest.getStock() == null || productRequest.getStock() < 0) {
            throw new BadRequestException("Stock cannot be negative");
        }

        Product product = toEntity(productRequest);
        productRepository.save(product);
        return toResponse(product);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return toResponse(product);
    }

    @Override
    public List<ProductResponse> search(String name, Double minPrice, Boolean inStock) {

        Specification<Product> spec = Specification
                .where(ProductSpecification.hasName(name))
                .and(ProductSpecification.priceGreaterThan(minPrice))
                .and(ProductSpecification.inStock(inStock));

        return productRepository.findAll(spec)
                .stream()
                .map(response -> toResponse(response))
                .toList();
    }

    public Product toEntity(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .build();
    }

    public ProductResponse toResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .build();
    }
}
