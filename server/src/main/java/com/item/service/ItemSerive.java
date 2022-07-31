package com.item.service;

import com.item.ResourceNotFoundException;
import com.item.entity.ItemEntity;
import com.item.model.ItemSearchModel;

import java.util.List;


public interface ItemSerive {

    ItemEntity saveItem(ItemEntity entity);

    List<ItemEntity> findAllItems(String status);

    ItemEntity findById(Integer itemId) throws ResourceNotFoundException;

    ItemEntity updateItem(Integer itemId, ItemEntity entity) throws ResourceNotFoundException;

    void deleteItem(Integer itemId) throws ResourceNotFoundException;

    ItemEntity updateOrderCount(Integer id);

    List<ItemEntity>  searchItems(ItemSearchModel model);
}
