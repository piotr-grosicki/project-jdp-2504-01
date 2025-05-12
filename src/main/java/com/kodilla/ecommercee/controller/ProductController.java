package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductAvailability;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("product", "test description", 10.00, ProductAvailability.AVAILABLE);
        products.add(product1);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product1 = new Product("product2", "test description", 10.00, ProductAvailability.AVAILABLE);
        return ResponseEntity.ok(product1);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product product1 = new Product(product.getName(), product.getDescription(), product.getPrice(), ProductAvailability.AVAILABLE);
        return ResponseEntity.ok(product1);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product product1 = new Product(product.getName(), product.getDescription(), product.getPrice(), ProductAvailability.AVAILABLE);
        return ResponseEntity.ok(product1);
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteproduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }
}
