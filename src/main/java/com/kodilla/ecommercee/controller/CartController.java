package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductAvailability;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PutMapping
    public Cart createCart() {
        return new Cart(1L, 1L);
    }

    @GetMapping("/{cartId}")
    public List<Product> getCartProducts(@PathVariable Long cartId) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product1", "Description1", 50.00, ProductAvailability.AVAILABLE));
        products.add(new Product("Product2", "Description2", 20.00, ProductAvailability.ARCHIVED));
        return products;
    }

    @PutMapping("/{cartId}")
    public Cart addProductsToCart(@PathVariable Long cartId, @RequestBody Product product) {
        Cart cart = new Cart(cartId, 1L);
        Product addedProduct = new Product (product.getName(), product.getDescription(), product.getPrice(), ProductAvailability.AVAILABLE);
        return cart;
    }

    @DeleteMapping
    public Cart deleteProductFromCart(Long cartId, Long productId) {
        return new Cart(cartId, 1L);
    }
}
