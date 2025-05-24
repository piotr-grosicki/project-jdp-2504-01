package com.kodilla.ecommercee.repository;


import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductGroupRepository extends CrudRepository<ProductGroup, Long> {

    Optional<ProductGroup> findByName(String name);

}
