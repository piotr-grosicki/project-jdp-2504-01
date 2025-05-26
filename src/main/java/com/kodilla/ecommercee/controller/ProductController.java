package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductAvailability;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto productDto1 = new ProductDto(
                1L, "product", "test description", BigDecimal.valueOf(10.00), ProductAvailability.AVAILABLE, 1L);
        productDtos.add(productDto1);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        ProductDto productDto1 = new ProductDto(
                2L, "product2", "test description", BigDecimal.valueOf(10.00), ProductAvailability.AVAILABLE, 2L);
        return ResponseEntity.ok(productDto1);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto productDto1 = new ProductDto(
                1L, productDto.getName(), productDto.getDescription(), productDto.getPrice(), ProductAvailability.AVAILABLE, productDto.getId());
        return ResponseEntity.ok(productDto1);
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        ProductDto productDto1 = new ProductDto(
                1L, productDto.getName(), productDto.getDescription(), productDto.getPrice(), ProductAvailability.AVAILABLE, productDto.getProductGroupId());
        return ResponseEntity.ok(productDto1);
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteproduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }
}
