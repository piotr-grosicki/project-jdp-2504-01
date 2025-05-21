package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    private ProductGroup productGroup;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        productGroup = new ProductGroup();
        productGroup.setName("Electronics");
        entityManager.persist(productGroup);

        sampleProduct = new Product(
                null,
                "Laptop",
                "Gaming laptop",
                new BigDecimal("2999.99"),
                ProductAvailability.AVAILABLE,
                null, // cartItems
                productGroup  // <- jeśli uwzględniasz relację
        );
        productRepository.save(sampleProduct);
    }

    @AfterEach
    void cleanUp() {
        productRepository.deleteAll();
    }

    @Test
    void shouldCreateAndFetchProduct() {
        Product product = new Product(1L, "Laptop", "Gaming", new BigDecimal("2999.99"),
                ProductAvailability.AVAILABLE, new ArrayList<>(), productGroup);

        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(product.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals("Laptop", foundProduct.get().getName());
        assertEquals("Gaming", foundProduct.get().getDescription());
        assertEquals(productGroup.getId(), foundProduct.get().getProductGroup().getId());
    }

    @Test
    void shouldReadProductById() {

        Optional<Product> fetched = productRepository.findById(sampleProduct.getId());

        assertTrue(fetched.isPresent(), "Product should be found by ID");
        assertEquals("Laptop", fetched.get().getName());
        assertEquals("Gaming laptop", fetched.get().getDescription());
        assertEquals(new BigDecimal("2999.99"), fetched.get().getPrice());
        assertEquals(ProductAvailability.AVAILABLE, fetched.get().getProductAvailability());
        assertEquals(productGroup.getId(), fetched.get().getProductGroup().getId());
    }

    @Test
    void shouldSaveProductWithCartItems() {
        Product product = new Product(1L, "Monitor", "4K", new BigDecimal("1999.99"),
                ProductAvailability.AVAILABLE, new ArrayList<>(), productGroup);

        product = productRepository.save(product);
        product.setPrice(new BigDecimal("120"));
        productRepository.save(product);

        Product updated = productRepository.findById(product.getId()).orElseThrow();
        assertEquals("Monitor", updated.getName());
        assertEquals(new BigDecimal("120"), updated.getPrice());
    }

    @Test
        void shouldDeleteProduct() {
        Product product = new Product(1L, "Monitor", "4K", new BigDecimal("1999.99"),
                ProductAvailability.AVAILABLE, new ArrayList<>(), productGroup);

        product = productRepository.save(product);
        Long productId = product.getId();
        productRepository.deleteById(productId);

        assertTrue(productRepository.findById(productId).isEmpty());
    }
}
