package com.kodilla.ecommercee.repository;


import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
}
