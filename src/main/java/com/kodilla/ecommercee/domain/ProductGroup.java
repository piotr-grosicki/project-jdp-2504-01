package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_groups")
public final class ProductGroup {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    @NotNull
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany
            (
            targetEntity = Product.class,
            mappedBy = "productGroupId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products;
}