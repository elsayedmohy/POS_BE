package com.sayed.pos.modules.category.service;

import com.sayed.pos.modules.category.dto.CategoryResponse;
import com.sayed.pos.modules.category.mapper.CategoryMapper;
import com.sayed.pos.modules.category.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryResponse> findAll(Long storeId) {
        return categoryRepository.findAllByStoreId(storeId)
                .stream()
                .map(categoryMapper::toResponseDto).toList();
    }
}
