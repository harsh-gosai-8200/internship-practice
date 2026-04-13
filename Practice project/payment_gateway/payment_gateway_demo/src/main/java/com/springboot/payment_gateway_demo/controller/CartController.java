package com.springboot.payment_gateway_demo.controller;

import com.springboot.payment_gateway_demo.DTO.AddToCartRequest;
import com.springboot.payment_gateway_demo.DTO.CartResponse;
import com.springboot.payment_gateway_demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartResponse getCart(@RequestParam String sessionId) {
        return cartService.getCart(sessionId);
    }

    @PostMapping("/add")
    public CartResponse addToCart(@RequestParam String sessionId,
                                  @Valid @RequestBody AddToCartRequest request) {
        return cartService.addToCart(sessionId, request.getProductId(), request.getQuantity());
    }

    @DeleteMapping("/remove")
    public CartResponse removeFromCart(@RequestParam String sessionId,
                                       @RequestParam Long productId) {
        return cartService.removeFromCart(sessionId, productId);
    }

    @PutMapping("/update")
    public CartResponse updateQuantity(@RequestParam String sessionId,
                                       @RequestParam Long productId,
                                       @RequestParam Integer quantity) {
        return cartService.updateQuantity(sessionId, productId, quantity);
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestParam String sessionId) {
        cartService.clearCart(sessionId);
    }
}