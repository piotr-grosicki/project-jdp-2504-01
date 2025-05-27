package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductAvailability;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductGroupRepository productGroupRepository;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = products.stream()
                .map(productMapper::mapToProductDto)
                .toList();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(productMapper.mapToProductDto(product));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductGroup group = productGroupRepository.findById(productDto.getCategoryId()).orElseThrow();
        Product product = productMapper.mapToProduct(productDto, group);
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.saveProduct(product)));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        ProductGroup group = productGroupRepository.findById(productDto.getCategoryId()).orElseThrow();
        Product product = productMapper.mapToProduct(productDto, group);
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.saveProduct(product)));
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteproduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
