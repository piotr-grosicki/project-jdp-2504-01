package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.domain.ProductGroupDto;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductGroupMapper {

    public ProductGroup mapToProductGroup(final ProductGroupDto productGroupDto) {
        ProductGroup productGroup = new ProductGroup(productGroupDto.getId(),
                productGroupDto.getName(), productGroupDto.getDescription(),null);
//        if (productGroupDto.getId() != null) {
//            productGroup.setId(productGroupDto.getId());
//        }
        return productGroup;
    }

    public ProductGroupDto mapToProductGroupDto(final ProductGroup productGroup) {
        return new ProductGroupDto(productGroup.getId(), productGroup.getName(),
                productGroup.getDescription(), productGroup.getProducts() );
    }

    public List<ProductGroupDto> mapToProductGroupDtoList(final List<ProductGroup> productGroups) {
        return productGroups.stream()
                .map(this::mapToProductGroupDto)
                .toList();
    }
}