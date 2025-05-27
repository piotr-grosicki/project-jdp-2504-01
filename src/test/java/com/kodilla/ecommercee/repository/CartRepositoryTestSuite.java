package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CartRepositoryTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private UserRepository userRepository;

    private Product product1;

    private Product product2;

    private Cart cart;

    private CartItem cartItem1;

    private CartItem cartItem2;

    private User user;

    @BeforeEach
    void setUp() {
        ProductGroup productGroup = new ProductGroup(
                "Group1",
                "Desc1"
        );
        productGroupRepository.save(productGroup);

        product1 = new Product(
                1L,
                "Product1",
                "Desc1",
                new BigDecimal("100.00"),
                ProductAvailability.AVAILABLE,
                null,
                productGroup
        );
        productRepository.save(product1);

        product2 = new Product(
                2L,
                "Product2",
                "Desc1=2",
                new BigDecimal("200.00"),
                ProductAvailability.AVAILABLE,
                null,
                productGroup
        );
        productRepository.save(product2);

        user = new User();
        user.setBlocked(false);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);


        cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        cartItem1 = new CartItem();
        cartItem1.setCart(cart);
        cartItem1.setProduct(product1);
        cartItem1.setQuantity(5);
        cartItemRepository.save(cartItem1);

        cart.getCartItems().add(cartItem1);
        cartRepository.save(cart);
    }

    @AfterEach
    void cleanUp() {
        cartItemRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        productGroupRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testAddCartItemToCart() {
        // When
        cart = cartRepository.findAll().iterator().next();

        cartItem2 = new CartItem();
        cartItem2.setCart(cart);
        cartItem2.setProduct(product2);
        cartItem2.setQuantity(3);
        cartItemRepository.save(cartItem2);
        cart.getCartItems().add(cartItem2);

        // Then
        assertEquals(2, cart.getCartItems().size());
        assertEquals(cartItem1, cart.getCartItems().get(0));
    }

    @Test
    void testRemoveCartItemFromCart() {
        // When
        cart = cartRepository.findAll().iterator().next();

        cart.getCartItems().remove(cartItem1);

        // Then
        assertTrue(cart.getCartItems().isEmpty());
    }
}
