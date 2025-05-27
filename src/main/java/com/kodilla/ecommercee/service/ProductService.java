package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(final Long id) {
        return productRepository.findById(id).orElseThrow(() -> new com.kodilla.ecommercee.exception.ProductNotFoundException(id));
    }

        public Product saveProduct(final Product product) {
            return productRepository.save(product);
        }

    public void deleteProduct(final Long id) {
            productRepository.deleteById(id);
        }
    }
