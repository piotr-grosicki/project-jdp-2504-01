package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PostMapping
    public CartDto createCart() {
        return new CartDto(1L, 1L, List.of());
    }

    @GetMapping("/{cartId}")
    public List<ProductDto> getCartProducts(@PathVariable Long cartId) {
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(
                cartId, "Product1", "Description1", BigDecimal.valueOf(50.00), ProductAvailability.AVAILABLE, 1L));
        products.add(new ProductDto(
                cartId, "Product2", "Description2", BigDecimal.valueOf(20.00), ProductAvailability.ARCHIVED, 2L));
        return products;
    }

    @PutMapping("/{cartId}/{productId}")
    public CartDto addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return new CartDto(cartId, 1L, List.of(productId));
    }

    @DeleteMapping("/{cartId}/{productId}")
    public CartDto deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        CartDto cartDto = new CartDto(cartId, 1L, new ArrayList<>(Arrays.asList(3L, 4L, 6L, productId)));
        cartDto.getProductsIds().remove(productId);
        return cartDto;
    }

    @PostMapping("/{cartId}")
    public Order createOrder(@PathVariable Long cartId) {
        return new Order(1L, BigDecimal.valueOf(100.00), "Elm street", LocalDateTime.now(), OrderStatus.COMPLETED,
                new User(1L, "FirstName", "LastName", "email@email.com", false, LocalDateTime.now(), new ArrayList<>(),
                        new Cart(cartId, 1L, List.of())));
    }
}
