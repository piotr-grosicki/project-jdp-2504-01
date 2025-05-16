package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductAvailability;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = new ArrayList<>();
        Product product = new Product("Mock product", "Sample description", 99.99, ProductAvailability.AVAILABLE);
        product.setId(1L);
        products.add(product.toDto());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        Product product = new Product("Mock product " + productId, "Description for product " + productId, 49.99, ProductAvailability.AVAILABLE);
        product.setId(productId);
        return ResponseEntity.ok(product.toDto());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = Product.fromDto(productDto);
        product.setId(100L);
        return ResponseEntity.ok(product.toDto());
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        Product product = Product.fromDto(productDto);
        return ResponseEntity.ok(product.toDto());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build(); // No real deletion
    }}
