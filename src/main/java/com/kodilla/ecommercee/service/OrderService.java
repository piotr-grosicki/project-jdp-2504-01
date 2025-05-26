package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    public Order createOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        Order order = new Order(
                null,
                orderDto.getTotalPrice(),
                orderDto.getAddress(),
                orderDto.getPurchaseDate(),
                orderDto.getOrderStatus(),
                user
        );

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, OrderDto orderDto) {
        Order existingOrder = getOrderById(id);

        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        existingOrder.setTotalPrice(orderDto.getTotalPrice());
        existingOrder.setAddress(orderDto.getAddress());
        existingOrder.setPurchaseDate(orderDto.getPurchaseDate());
        existingOrder.setOrderStatus(orderDto.getOrderStatus());
        existingOrder.setUser(user);

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NoSuchElementException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}
