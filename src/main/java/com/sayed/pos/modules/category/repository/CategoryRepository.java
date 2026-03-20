package com.sayed.pos.modules.category.repository;

import com.sayed.pos.modules.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
