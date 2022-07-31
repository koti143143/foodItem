package com.item.controller;

import com.item.ResourceNotFoundException;
import com.item.entity.ItemEntity;
import com.item.model.ItemSearchModel;
import com.item.service.ItemSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemSerive serive;

    @PostMapping("/items")
    public ResponseEntity<ItemEntity> saveItem(@RequestBody ItemEntity entity) {
        return ResponseEntity.ok(serive.saveItem(entity));
    }

    @GetMapping("/items/list/{status}")
    public ResponseEntity<List<ItemEntity>> findAllItems(@PathVariable(value = "status") String status) {
        return ResponseEntity.ok(serive.findAllItems(status));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable(value = "id") Integer itemId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(serive.findById(itemId));
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable(value = "id") Integer itemId,
                                                       @RequestBody ItemEntity entity)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(serive.updateItem(itemId, entity));
    }

    @DeleteMapping("/items/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Integer itemId)
            throws ResourceNotFoundException {
        serive.deleteItem(itemId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/items/search")
    public ResponseEntity<List<ItemEntity>> customSearch(@RequestBody ItemSearchModel model) {
        return ResponseEntity.ok(serive.searchItems(model));
    }
}

