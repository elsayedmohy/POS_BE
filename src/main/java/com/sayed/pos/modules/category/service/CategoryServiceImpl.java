package com.sayed.pos.modules.category.service;

import com.sayed.pos.common.exception.ResourceNotFoundException;
import com.sayed.pos.modules.category.dto.CategoryRequest;
import com.sayed.pos.modules.category.dto.CategoryResponse;
import com.sayed.pos.modules.category.mapper.CategoryMapper;
import com.sayed.pos.modules.category.repository.CategoryRepository;
import com.sayed.pos.modules.category.dto.CategoryRequest;
import com.sayed.pos.modules.category.dto.CategoryResponse;
import com.sayed.pos.modules.store.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryResponse> findAll(Long storeId) {
        return categoryRepository.findAllByStoreId(storeId)
                .stream()
                .map(categoryMapper::toResponseDto).toList();
    }

    @Override
    public CategoryResponse findById(Long categoryId, Long storeId)  {
        var  category = categoryRepository.findByIdAndStoreId(categoryId,storeId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public void deleteById(Long categoryId ,Long storeId) {
        var  category = categoryRepository.findByIdAndStoreId(categoryId,storeId).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException("Product not found");
        }
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryResponse create(Long storeId , CategoryRequest request) {
        var store = storeRepository.findById(storeId).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        var category = categoryMapper.toEntity(request);
        category.setStore(store);
        categoryRepository.save(category);
        return  categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponse update(Long storeId , Long categoryId ,CategoryRequest request) {
        var category = categoryRepository.findByIdAndStoreId(categoryId,storeId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        category.setName(request.getName());
        var updated = categoryRepository.save(category);
        return  categoryMapper.toResponseDto(updated);
    }
}
