package com.kodilla.ecommercee.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Long id) {
        super("Cart id " + id + " not found");
    }
}