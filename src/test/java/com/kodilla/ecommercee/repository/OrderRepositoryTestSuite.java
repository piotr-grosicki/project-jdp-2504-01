package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void prepareData() {
        testUser = new User();
        testUser.setBlocked(false);
        testUser.setEmail("test@test.com");
        testUser.setFirstName("Test");
        testUser.setLastName("User");
        testUser.setCreatedAt(LocalDateTime.now());

        userRepository.save(testUser);

        Order order1 = new Order(1L, new BigDecimal("100.00"), "Elm Street", LocalDateTime.now(), OrderStatus.COMPLETED, testUser);
        Order order2 = new Order(2L,new BigDecimal("200.00"), "Maple Street", LocalDateTime.now(), OrderStatus.COMPLETED, testUser);
        orderRepository.save(order1);
        orderRepository.save(order2);
    }

    @AfterEach
    void cleanDatabase() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldFindAllOrders() {
        // when
        List<Order> orders = (List<Order>) orderRepository.findAll();

        // then
        assertEquals(2, orders.size());
        assertTrue(orders.stream().anyMatch(o -> o.getAddress().equals("Elm Street")));
        assertTrue(orders.stream().anyMatch(o -> o.getAddress().equals("Maple Street")));
    }

    @Test
    void shouldReadSingleOrderById() {
        // given
        Order order = orderRepository.findAll().iterator().next();

        // when
        Optional<Order> retrieved = orderRepository.findById(order.getId());

        // then
        assertTrue(retrieved.isPresent());
        assertEquals(order.getAddress(), retrieved.get().getAddress());
        assertEquals(testUser.getId(), retrieved.get().getUser().getId());
    }

    @Test
    void shouldUpdateOrderAddress() {
        // given
        Order order = orderRepository.findAll().iterator().next();
        String newAddress = "High Street";

        // when
        order.setAddress(newAddress);
        orderRepository.save(order);

        // then
        Order updated = orderRepository.findById(order.getId()).orElseThrow();
        assertEquals(newAddress, updated.getAddress());
    }

    @Test
    void shouldDeleteOrderById() {
        // given
        Order order = orderRepository.findAll().iterator().next();
        Long id = order.getId();

        // when
        orderRepository.deleteById(id);
        Optional<Order> deleted = orderRepository.findById(id);

        // then
        assertTrue(deleted.isEmpty());
    }

    @Test
    void shouldCreateNewOrder() {
        // given
        Order newOrder = new Order(null, new BigDecimal("300.00"), "Sunset Blvd", LocalDateTime.now(), OrderStatus.COMPLETED, testUser);

        // when
        Order savedOrder = orderRepository.save(newOrder);

        // then
        assertNotNull(savedOrder.getId());
        assertEquals("Sunset Blvd", savedOrder.getAddress());
        assertEquals(OrderStatus.COMPLETED, savedOrder.getOrderStatus());
        assertEquals(testUser.getId(), savedOrder.getUser().getId());
    }
}
