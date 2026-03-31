package com.sayed.pos.modules.category.service;

import com.sayed.pos.modules.category.dto.CategoryRequest;
import com.sayed.pos.modules.category.dto.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CategoryService {
    List<CategoryResponse> findAll(Long storeId);

    CategoryResponse findById(Long categoryId, Long storeId);

    void deleteById(Long categoryId, Long storeId);

    CategoryResponse create(Long storeId, CategoryRequest request);

    CategoryResponse update(Long storeId, Long categoryId, CategoryRequest request);
}
