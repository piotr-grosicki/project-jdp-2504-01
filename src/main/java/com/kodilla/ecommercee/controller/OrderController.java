package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final Map<Long, Order> orders = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1); // generator PK

    public OrderController() {
        //2 sample orders to start
        // PK = id, FK = userId
        orders.put(1L, new Order(1L, 10L, "qwe123", "2025-12-31 T23:00:00")); // id = PK, userId = FK
        orders.put(2L, new Order(2L, 11L, "asd456", "2025-12-31 T22:00:00"));
        idGenerator.set(3);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order newOrder) {
        Long newId = idGenerator.getAndIncrement(); // PK
        // FK - userId
        Order order =new Order(newId, newOrder.getUserId(), newOrder.getToken(), newOrder.getExpiresAt());
        orders.put(newId, order);
        return order;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        Order order = orders.get(id); // PK = id
        if (order == null) {
            throw new NoSuchElementException("Order with ID " + id + " not found");
        }
        return order;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        if(!orders.containsKey(id)) {
            throw new NoSuchElementException("Order with ID " + id + " not found");
        }
        // PK = id, FK = userId
        Order order = new Order(id, updatedOrder.getUserId(), updatedOrder.getToken(), updatedOrder.getExpiresAt());
        orders.put(id, order);
        return order;
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        if (orders.remove(id) == null) {
            throw new NoSuchElementException("Order with ID " + id + " not found");
        }
        return "Order with ID " + id + " deleted (mock)";
    }
}
