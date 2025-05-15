package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    public Product(String name, String description, Double price, ProductAvailability productAvailability) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productAvailability = productAvailability;
//        this.productGroup = productGroup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", nullable = false)
    private ProductAvailability productAvailability;

//    @ManyToOne
//    @JoinColumn(name = "product_group_id", nullable = false)
//    private ProductGroup productGroup;
}