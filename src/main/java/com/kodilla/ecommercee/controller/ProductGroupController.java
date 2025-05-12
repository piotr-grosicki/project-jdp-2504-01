package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class ProductGroupController {

    @GetMapping
    public List<ProductGroup> getProductGroups() {
        List<ProductGroup> productGroups = new ArrayList<>();
        productGroups.add(new ProductGroup(1L, "Group1", "Description1"));
        return productGroups;
    }

    @GetMapping(value = "{groupId}")
    public ProductGroup getProductGroup(@PathVariable Long groupId) {
        return new ProductGroup(2L, "Group2", "Description2");
    }

    @PostMapping
    public ProductGroup addProductGroup(@RequestBody ProductGroup productGroup) {
        return new ProductGroup(productGroup.getId(), productGroup.getName(), productGroup.getDescription());
    }

    @PutMapping
    public ProductGroup updateProductGroup(@RequestBody ProductGroup productGroup) {
        return new ProductGroup(productGroup.getId(), productGroup.getName(), productGroup.getDescription());
    }
}
