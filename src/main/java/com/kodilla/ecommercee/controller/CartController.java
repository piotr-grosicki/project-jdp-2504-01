package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartMapper cartMapper;
    private final CartDbService cartDbService;
    private final ProductMapper productMapper;
    private final ProductController productController;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartDbService.createCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<Product>> getCartProducts(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartDbService.getCart(cartId)).getProducts());
    }

    @PutMapping(value = "{cartId}/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long cartId,
                                                 @PathVariable Long productId) throws CartNotFoundException {
        Product product = productMapper.mapToProduct(Objects.requireNonNull(productController.getProduct(productId).getBody()));
        cartDbService.getCart(cartId).getProducts().add(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/{productId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long cartId,
                                                      @PathVariable Long productId) throws CartNotFoundException {
        Product product = productMapper.mapToProduct(Objects.requireNonNull((productController.getProduct(productId)).getBody()));
        cartDbService.getCart(cartId).getProducts().remove(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}")
    public Order createOrder(@PathVariable Long cartId) {
        return new Order(null, BigDecimal.valueOf(100.00), "Elm street", LocalDateTime.now(), OrderStatus.COMPLETED);
    }
}
