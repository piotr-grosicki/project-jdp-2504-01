package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", nullable = false)
    private ProductAvailability productAvailability;

    @ManyToOne
    @JoinColumn(name = "product_group_id", nullable = false)
    private ProductGroup productGroupId;

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Cart> carts;

    public Product(Long id, String name, String description, BigDecimal price,
                   ProductAvailability productAvailability, Long productGroup) {
    }
}