package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getTotalPrice(),
                order.getAddress(),
                order.getPurchaseDate(),
                order.getOrderStatus(),
                order.getUser().getId()
        );
    }

    public Order mapToOrder(OrderDto orderDto, User user) {
        return new Order(
                null,
                orderDto.getTotalPrice(),
                orderDto.getAddress(),
                orderDto.getPurchaseDate(),
                orderDto.getOrderStatus(),
                user
        );
    }
}
