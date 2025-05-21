package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

//    @OneToOne
//    @JoinColumn(name = "cart_id", nullable = false)
//    private Cart cart;
}