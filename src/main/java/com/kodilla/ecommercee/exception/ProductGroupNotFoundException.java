package com.kodilla.ecommercee.exception;

public class ProductGroupNotFoundException extends Exception {
public ProductGroupNotFoundException(Long id) {
        super("Product group with id " + id + " not found");
    }
}