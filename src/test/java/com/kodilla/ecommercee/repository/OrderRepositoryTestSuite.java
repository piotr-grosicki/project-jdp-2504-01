package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveOrders() {
        Order order1 = new Order(100.00, "Elm street", LocalDateTime.now(), OrderStatus.COMPLETED);
        Order order2 = new Order(120.00, "Palm street", LocalDateTime.now(), OrderStatus.COMPLETED);

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .toList();
        assertEquals(2, orders.size());

        orderRepository.deleteAll();
        orders = StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .toList();
        assertEquals(0, orders.size());
    }
}
