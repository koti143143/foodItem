package com.item;

import com.item.entity.ItemEntity;
import com.item.repository.ItemRepository;
import com.item.service.ItemServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FoodItemApplicationTests {

    @MockBean
    private ItemRepository repository;

    @Autowired
    private ItemServiceImpl service;

    ItemEntity entity;
    ItemEntity savedEntity;
    @BeforeEach
    public void setup() {
        entity = new ItemEntity();
        entity.setCategory("veg");
        entity.setCount(2);
        entity.setInstructions("clean rice");
        entity.setIngredients("rice, water");
        entity.setName("palav");

        savedEntity = new ItemEntity();
        savedEntity.setCategory("veg");
        savedEntity.setCount(2);
        savedEntity.setInstructions("clean rice");
        savedEntity.setIngredients("rice, water");
        savedEntity.setName("palav");
        savedEntity.setId(2);

    }

    @Test
    void saveEntity() {
        Mockito.when(repository.save(entity)).thenReturn(savedEntity);
        ItemEntity res = service.saveItem(entity);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(2, res.getId());
    }

}
