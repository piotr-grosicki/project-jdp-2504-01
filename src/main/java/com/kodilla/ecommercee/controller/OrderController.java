package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.OrderStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final Map<Long, OrderDto> orders = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1); // generator PK

    public OrderController() {
        orders.put(1L, new OrderDto(1L, 100.00, "Elm street", LocalDateTime.now(), OrderStatus.COMPLETED));
        orders.put(2L, new OrderDto(2L, 150.00, "Maple street", LocalDateTime.now(), OrderStatus.COMPLETED));
        idGenerator.set(3);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        Long newId = idGenerator.getAndIncrement();
        orderDto.setId(newId);
        orders.put(newId, orderDto);
        return orderDto;
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        OrderDto order = orders.get(id);
        if (order == null) {
            throw new NoSuchElementException("Order with ID " + id + " not found");
        }
        return order;
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto updatedOrder) {
        if (!orders.containsKey(id)) {
            throw new NoSuchElementException("Order with ID " + id + " not found");
        }
        updatedOrder.setId(id);
        orders.put(id, updatedOrder);
        return updatedOrder;
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        if (orders.remove(id) == null) {
            throw new NoSuchElementException("Order with ID " + id + " not found");
        }
        return "Order with ID " + id + " deleted (mock)";
    }
}
