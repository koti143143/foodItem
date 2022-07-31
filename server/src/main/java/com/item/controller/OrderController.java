package com.item.controller;

import com.item.ResourceNotFoundException;
import com.item.entity.ItemEntity;
import com.item.service.ItemSerive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class OrderController {

    private ItemSerive itemSerive;
    @GetMapping("/order/{id}")
    public ResponseEntity<ItemEntity> updateOrderCount(@PathVariable("id") Integer id)
        throws ResourceNotFoundException {
        return ResponseEntity.ok(itemSerive.updateOrderCount(id));
    }
}
