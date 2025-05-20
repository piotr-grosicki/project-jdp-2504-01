package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void prepareData() {
        Order order1 = new Order(new BigDecimal("100"), "Elm Street", LocalDateTime.now(), OrderStatus.COMPLETED);
        Order order2 = new Order(new BigDecimal("200"), "Elm Street", LocalDateTime.now(), OrderStatus.COMPLETED);
        orderRepository.save(order1);
        orderRepository.save(order2);
    }

    @AfterEach
    void cleanDatabase() {
        orderRepository.deleteAll();
    }

    @Test
    void testCreateOrder() {
        Iterable<Order> ordersIterable = orderRepository.findAll();
        List<Order> orders = StreamSupport.stream(ordersIterable.spliterator(), false)
                .toList();
        assertEquals(2, orders.size());
    }

    @Test
    void testReadOrder() {
        Order order = orderRepository.findAll().iterator().next();
        assertEquals("Elm Street", order.getAddress());
    }

    @Test
    void testUpdateOrder() {
        Order order = orderRepository.findAll().iterator().next();
        order.setAddress("High Street");
        orderRepository.save(order);

        order = orderRepository.findById(order.getId()).get();
        assertEquals("High Street", order.getAddress());
    }

    @Test
    void testDeleteOrder() {
        Order order = orderRepository.findAll().iterator().next();
        Long id = order.getId();
        orderRepository.deleteById(id);
        Optional<Order> orderOpt = orderRepository.findById(id);
        assertTrue(orderOpt.isEmpty());
    }
}
