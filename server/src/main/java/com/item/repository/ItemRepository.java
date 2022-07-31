package com.item.repository;

import com.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {

    @Query("select c from ItemEntity c where " +
            "c.ingredients not like '%'||:ingredients||'%' and c.instructions like '%'||:instructions||'%'")
    List<ItemEntity> findByIngredientsNotLikeAndInstructionsLike(String ingredients, String instructions);
}
