package com.kodilla.ecommercee.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Entity(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    public Order(Double totalPrice, String address, LocalDateTime purchaseDate, OrderStatus orderStatus) {
        this.totalPrice = totalPrice;
        this.address = address;
        this.purchaseDate = purchaseDate;
        this.orderStatus = orderStatus;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "order_status")
    private OrderStatus orderStatus;
    //Relations to other entities will be implemented later when those entities are complete
    //    private Long userId;
    //    private Long cartId;



}
