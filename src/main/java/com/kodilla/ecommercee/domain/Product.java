package com.kodilla.ecommercee.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import org.springframework.data.annotation.Id;


@Getter
public class Product {

    public Product(String name, String description, Double price, ProductAvailability productAvailability) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productAvailability = productAvailability;
    }

    private Long id;

    private String name;

    private String description;

    private Double price;

    private ProductAvailability productAvailability;
}
