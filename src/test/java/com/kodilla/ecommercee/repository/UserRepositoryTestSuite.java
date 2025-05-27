package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setFirstName("Janek");
        sampleUser.setLastName("Nowak");
        sampleUser.setEmail("janek@nowak.com");
        sampleUser.setBlocked(false);
        sampleUser.setCreatedAt(LocalDateTime.now());

        sampleUser = userRepository.save(sampleUser);

        Cart cart = new Cart();
        cart.setUser(sampleUser);
        cart.setCartItems(List.of());

        sampleUser.setCart(cart);

        cartRepository.save(cart);
    }

    @AfterEach
    void cleanUp() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    void shouldCreateAndFetchUser() {
        // when
        Optional<User> user = userRepository.findById(sampleUser.getId());

        //then
        assertTrue(user.isPresent());
        assertEquals("Janek", user.get().getFirstName());
        assertEquals("Nowak", user.get().getLastName());
        assertEquals("janek@nowak.com", user.get().getEmail());
        assertFalse(user.get().isBlocked());
        assertNotNull(user.get().getCart());
    }

    @Test
    void shouldUpdateUserEmail() {
        // given
        sampleUser.setEmail("new.email@example.com");

        // when
        userRepository.save(sampleUser);

        // then
        User updated = userRepository.findById(sampleUser.getId()).orElseThrow();
        assertEquals("new.email@example.com", updated.getEmail());
    }

    @Test
    void shouldBlockUser() {
        // given
        sampleUser.setBlocked(true);

        // when
        userRepository.save(sampleUser);

        // then
        User blocked = userRepository.findById(sampleUser.getId()).orElseThrow();
        assertTrue(blocked.isBlocked());
    }

    @Test
    void shouldDeleteUserAndCascadeCart() {
        // given
        Long userId = sampleUser.getId();
        Long cartId = sampleUser.getCart().getId();

        // when
        userRepository.deleteById(userId);

        // then
        assertFalse(userRepository.findById(userId).isPresent());
        assertFalse(cartRepository.findById(cartId).isPresent());
    }

    @Test
    void shouldAddOrderToUser() {
        //given
        Order order = new Order();
        order.setTotalPrice(new BigDecimal("199.99"));
        order.setAddress("Test Street 1");
        order.setPurchaseDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setUser(sampleUser);

        // when
        order = orderRepository.save(order);

        // then
        Optional<Order> found = orderRepository.findById(order.getId());

        assertTrue(found.isPresent());
        assertEquals("Test Street 1", found.get().getAddress());
        assertEquals(sampleUser.getId(), found.get().getUser().getId());
    }
}
