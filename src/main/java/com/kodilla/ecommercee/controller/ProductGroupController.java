package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.domain.ProductGroupDto;
import com.kodilla.ecommercee.exception.ProductGroupNotFoundException;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.service.ProductGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class ProductGroupController {

    private final ProductGroupService service;
    private final ProductGroupMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getProductGroups() {
        List<ProductGroup> productGroups = service.getAllProductGroups();
        return ResponseEntity.ok(mapper.mapToProductGroupDtoList(productGroups));
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<ProductGroupDto> getProductGroup(@PathVariable Long groupId) throws ProductGroupNotFoundException {
        return ResponseEntity.ok(mapper.mapToProductGroupDto(service.getProductGroupById(groupId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductGroupDto> addProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        ProductGroup productGroup = mapper.mapToProductGroup(productGroupDto);
        ProductGroup savedProductGroup = service.createProductGroup(productGroup);
        return ResponseEntity.ok(mapper.mapToProductGroupDto(savedProductGroup));
    }

    @PutMapping
    public ResponseEntity<ProductGroupDto> updateProductGroup(@RequestBody ProductGroupDto productGroupDto) throws ProductGroupNotFoundException {
        ProductGroup productGroup = mapper.mapToProductGroup(productGroupDto);
        ProductGroup updatedProductGroup = service.updateProductGroup(productGroup);
        return ResponseEntity.ok(mapper.mapToProductGroupDto(updatedProductGroup));
    }
}
