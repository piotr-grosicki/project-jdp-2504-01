package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ProductGroupDto {
    private Long id;
    private String name;
    private String description;
}