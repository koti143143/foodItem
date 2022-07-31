package com.item.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.entity.ItemEntity;
import com.item.repository.ItemRepository;
import com.item.service.ItemSerive;
import com.item.service.ItemServiceImpl;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static net.bytebuddy.matcher.ElementMatchers.is;


@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"com.item"})
public class ItemControllerTest {

    @Autowired
    MockMvc  mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ItemRepository repository;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private List<ItemEntity> list;

    @LocalServerPort
    String randomServerPort;


    @BeforeEach
    public void setup() {
        this.list = new ArrayList<>();
        ItemEntity it = new ItemEntity();
        it.setId(1);
        it.setCategory("veg");
        it.setCount(2);
        it.setInstructions("clean rice");
        it.setIngredients("rice, water");
        it.setName("palav");
        this.list.add(it);
    }

    @Test
    public void testGetAlItems() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(this.list);
        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/items/list/load")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
    }
}
