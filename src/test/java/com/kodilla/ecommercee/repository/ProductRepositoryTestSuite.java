package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    private ProductGroup productGroup;

    private Product sampleProduct;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        productGroup = new ProductGroup();
        productGroup.setName("Electronics");
        productGroup.setDescription("Electronic devices");
        productGroup = productGroupRepository.save(productGroup);

        sampleProduct = new Product(
                null,
                "Laptop",
                "Gaming laptop",
                new BigDecimal("2999.99"),
                ProductAvailability.AVAILABLE,
                null,
                productGroup
        );
        sampleProduct = productRepository.save(sampleProduct);

        sampleUser = new User();
        sampleUser.setFirstName("Janek");
        sampleUser.setLastName("Nowak");
        sampleUser.setEmail("janek@nowak.com");
        sampleUser.setBlocked(false);
        sampleUser.setCreatedAt(LocalDateTime.now());

        sampleUser = userRepository.save(sampleUser);
    }

    @AfterEach
    void cleanUp() {
        cartItemRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        productGroupRepository.deleteAll();
    }

    @Test
    void shouldCreateAndFetchProduct() {
        // when
        Optional<Product> foundProduct = productRepository.findById(sampleProduct.getId());
        // then
        assertTrue(foundProduct.isPresent());
        assertEquals("Laptop", foundProduct.get().getName());
        assertEquals("Gaming laptop", foundProduct.get().getDescription());
        assertEquals(productGroup.getId(), foundProduct.get().getProductGroup().getId());
    }

    @Test
    void shouldReadProductById() {
        // when
        Optional<Product> fetched = productRepository.findById(sampleProduct.getId());
        // then
        assertTrue(fetched.isPresent(), "Product should be found by ID");
        assertEquals("Laptop", fetched.get().getName());
        assertEquals("Gaming laptop", fetched.get().getDescription());
        assertEquals(new BigDecimal("2999.99"), fetched.get().getPrice());
        assertEquals(ProductAvailability.AVAILABLE, fetched.get().getProductAvailability());
        assertEquals(productGroup.getId(), fetched.get().getProductGroup().getId());
    }

    @Test
    void shouldUpdateProductPrice() {
        // given
        sampleProduct.setPrice(new BigDecimal("2500.00"));
        // when
        productRepository.save(sampleProduct);
        // then
        Product updated = productRepository.findById(sampleProduct.getId()).orElseThrow();
        assertEquals("Laptop", updated.getName());
        assertEquals(new BigDecimal("2500.00"), updated.getPrice());
    }

    @Test
    void shouldDeleteProduct() {
        // given
        Long id = sampleProduct.getId();
        // when
        productRepository.deleteById(id);
        // then
        assertTrue(productRepository.findById(id).isEmpty());
    }

    @Test
    void shouldCreateAndReadOrder() {
        // given

        Order order = new Order();
        order.setTotalPrice(new BigDecimal("1200.50"));
        order.setAddress("Test Street 1");
        order.setPurchaseDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setUser(sampleUser);

        // when
        order = orderRepository.save(order);

        // then
        Optional<Order> saved = orderRepository.findById(order.getId());

        assertTrue(saved.isPresent());
        assertEquals("Test Street 1", saved.get().getAddress());
        assertEquals(OrderStatus.COMPLETED, saved.get().getOrderStatus());
        assertEquals(new BigDecimal("1200.50"), saved.get().getTotalPrice());
    }
}