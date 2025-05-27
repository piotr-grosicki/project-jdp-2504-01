package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.exception.ProductGroupNotFoundException;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.domain.ProductGroup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupService {
    @Autowired
    private ProductGroupRepository repository;

    public List<ProductGroup> getAllProductGroups() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public ProductGroup getProductGroupById(Long id) throws ProductGroupNotFoundException {
        return repository.findById(id).orElseThrow(ProductGroupNotFoundException::new);
    }

    public ProductGroup createProductGroup(ProductGroup productGroup) {
        return repository.save(productGroup);
    }

    public ProductGroup updateProductGroup(ProductGroup productGroup) throws ProductGroupNotFoundException {
        if (repository.existsById(productGroup.getId())) {
            return repository.save(productGroup);
        } else {
            throw new ProductGroupNotFoundException();
        }
    }

}
