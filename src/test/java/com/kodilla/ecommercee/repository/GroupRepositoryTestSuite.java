package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class GroupRepositoryTestSuite {

    @Autowired
    private ProductGroupRepository productGroupRepository;
    private ProductGroup group1;
    private ProductGroup group2;

    @BeforeEach
    public void init() {
        group1 = new ProductGroup(1L,"Ships","Anything that floats except kayaks",null);
        group2 = new ProductGroup(2l,"Bombs","All kinds of bombs",null);
        productGroupRepository.save(group1);
        productGroupRepository.save(group2);
    }

    @AfterEach
    void cleanUp() {
        productGroupRepository.deleteAll();
    }

    @Test
    public void getGroupByIdTest() {
        //Given
        //When
        Optional<ProductGroup> group = productGroupRepository.findById(group2.getId());
        //Then
        assertEquals("Bombs", group.get().getName());
    }

    @Test
    public void addGroupTest() {
        //Given
        //When
        Optional<ProductGroup> group = productGroupRepository.findByName(group1.getName());
        //Then
        group.ifPresent(productGroup -> assertEquals("Ships", productGroup.getName()));
    }

    @Test
    public void listAllGroupsTest() {
        //Given
        //When
        Iterable<ProductGroup> groups = productGroupRepository.findAll();
        //Then
        AtomicInteger counter = new AtomicInteger();
        System.out.println("List of product groups: ");
        groups.forEach(pg -> System.out.println(pg.getName()));
        groups.forEach(pg -> counter.getAndIncrement());
        assertEquals(2, counter.get());
    }

    @Test
    public void updateGroupByIdTest() {
        //Given
        group2.setName("Bakery Products");
        group2.setDescription("Bread, rolls and others");
        //When
        productGroupRepository.save(group2);
        //Then
        assertEquals("Bakery Products",
                productGroupRepository.findById(group2.getId()).get().getName());
        assertEquals("Bread, rolls and others",
                productGroupRepository.findById(group2.getId()).get().getDescription());
    }
}