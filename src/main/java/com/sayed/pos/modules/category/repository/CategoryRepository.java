package com.sayed.pos.modules.category.repository;

import com.sayed.pos.modules.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.store.id = :storeId")
    List<Category> findAllByStoreId(Long storeId);
}
