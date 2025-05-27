package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductMapper {

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getProductAvailability(),
                product.getProductGroup().getId()
        );
    }

    public Product mapToProduct(ProductDto productDto, ProductGroup productGroup) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getProductAvailability(),
                new ArrayList<>(),
                productGroup
        );
    }
}
