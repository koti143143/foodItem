package com.item.service;

import com.item.ResourceNotFoundException;
import com.item.entity.ItemEntity;
import com.item.model.ItemSearchModel;
import com.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemSerive {

    @Autowired
    private ItemRepository repository;

    @Override
    public ItemEntity saveItem(ItemEntity entity) {
        return repository.save(entity);
    }

    @Override
    public List<ItemEntity> findAllItems(String status) {
        List<ItemEntity> response;
        switch (status) {
            case "create": {
                response = repository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
                break;
            }
            case "modify": {
                response = repository.findAll(Sort.by(Sort.Direction.DESC, "modifiedDate"));
                break;
            }
            default:
                response = repository.findAll();
        }
        return response;
    }

    @Override
    public ItemEntity findById(Integer itemId) throws ResourceNotFoundException {
        return repository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));
    }

    @Override
    public ItemEntity updateItem(Integer itemId, ItemEntity entity) throws ResourceNotFoundException {
        ItemEntity itemEntity = findById(itemId);
        itemEntity.setName(entity.getName());
        itemEntity.setCount(entity.getCount());
        itemEntity.setIngredients(entity.getIngredients());
        itemEntity.setInstructions(entity.getInstructions());
        itemEntity.setModifiedDate(LocalDate.now());
        return repository.save(itemEntity);
    }

    @Override
    public void deleteItem(Integer itemId) throws ResourceNotFoundException {
        ItemEntity itemEntity = findById(itemId);
        repository.delete(itemEntity);
    }

    @Override
    public ItemEntity updateOrderCount(Integer id) {
        ItemEntity entity = findById(id);
        entity.setCount(entity.getCount() + 1);
        entity.setModifiedDate(LocalDate.now());
        return saveItem(entity);
    }

    @Override
    public  List<ItemEntity> searchItems(ItemSearchModel model) {
        if(model.getIngredients().equals("")){
            model.setInclude(true);
        }
        if (! model.getInclude()) {
            return repository.findByIngredientsNotLikeAndInstructionsLike(model.getIngredients(),
                    model.getInstructions());
        } else {
            return searchWithIngredients(model);
        }

    }

    private ExampleMatcher createSearchCondition(ItemSearchModel model) {
        return ExampleMatcher
                .matchingAll()
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase())
                .withMatcher("count", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("ingredients", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("instructions", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnorePaths("name","createdDate","modifiedDate","id");

    }

    private List<ItemEntity> searchWithIngredients(ItemSearchModel model) {
        ExampleMatcher matcher = createSearchCondition(model);
        ItemEntity entity = new ItemEntity();
        entity.setCategory(model.getCategory());
        entity.setIngredients(model.getIngredients());
        entity.setInstructions(model.getInstructions());
        entity.setCount(model.getOrders());
        Example<ItemEntity> example = Example.of(entity,matcher);
        return repository.findAll(example);
    }
}
