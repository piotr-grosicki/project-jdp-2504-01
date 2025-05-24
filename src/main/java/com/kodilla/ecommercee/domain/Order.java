package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    public Order(BigDecimal totalPrice, String address, LocalDateTime purchaseDate, OrderStatus orderStatus, User user) {
        this.totalPrice = totalPrice;
        this.address = address;
        this.purchaseDate = purchaseDate;
        this.orderStatus = orderStatus;
        this.user = user;
    }

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
    //Relations to other entities will be implemented later when those entities are complete
    //    private Long userId;  <-- done as a User field below
    //    private Long cartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

}
