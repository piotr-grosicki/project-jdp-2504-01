package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDbService {

    @Autowired
    private final CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    public void createCart(final Cart cart) {
        cartRepository.save(cart);
    }

    public Cart getCart(final Long id) throws CartNotFoundException {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart updateCart(final Long id, final Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(final Long id) {
        cartRepository.deleteById(id);
    }
}