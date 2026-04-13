package com.springboot.payment_gateway_demo.service;

import com.springboot.payment_gateway_demo.DTO.CartItemResponse;
import com.springboot.payment_gateway_demo.DTO.CartResponse;
import com.springboot.payment_gateway_demo.Exception.BadRequestException;
import com.springboot.payment_gateway_demo.model.*;
import com.springboot.payment_gateway_demo.repository.CartRepository;
import com.springboot.payment_gateway_demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getOrCreateCart(String sessionId) {
        return cartRepository.findBySessionId(sessionId)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .sessionId(sessionId)
                                .build()
                ));
    }

    public CartResponse addToCart(String sessionId, Long productId, Integer quantity) {
        Cart cart = getOrCreateCart(sessionId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .product(product)
                    .quantity(quantity)
                    .cart(cart)
                    .build();
            cart.getItems().add(newItem);
        }

        Cart savedCart = cartRepository.save(cart);
        return toCartResponse(savedCart);
    }

    public CartResponse removeFromCart(String sessionId, Long productId) {
        Cart cart = getOrCreateCart(sessionId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        Cart savedCart = cartRepository.save(cart);
        return toCartResponse(savedCart);
    }

    public CartResponse updateQuantity(String sessionId, Long productId, Integer quantity) {
        Cart cart = getOrCreateCart(sessionId);

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    // Edge case: cannot exceed stock
                    if (quantity > item.getProduct().getStock()) {
                        throw new BadRequestException(
                                "Cannot add more than available stock: " + item.getProduct().getStock()
                        );
                    }
                    // Edge case: cannot set quantity below 1
                    if (quantity < 1) {
                        throw new BadRequestException("Quantity must be at least 1");
                    }
                    item.setQuantity(quantity);
                });

        Cart savedCart = cartRepository.save(cart);
        return toCartResponse(savedCart);
    }

    public CartResponse getCart(String sessionId) {
        Cart cart = getOrCreateCart(sessionId);
        return toCartResponse(cart);
    }

    public void clearCart(String sessionId) {
        Cart cart = getOrCreateCart(sessionId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    // Convert Cart entity to CartResponse DTO
    private CartResponse toCartResponse(Cart cart) {
        List<CartItemResponse> items = cart.getItems().stream()
                .map(item -> CartItemResponse.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .productDescription(item.getProduct().getDescription())
                        .productPrice(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return CartResponse.builder()
                .sessionId(cart.getSessionId())
                .items(items)
                .build();
    }
}