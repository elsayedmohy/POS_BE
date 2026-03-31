package com.sayed.pos.modules.category.service;

import com.sayed.pos.modules.category.dto.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CategoryService {
    List<CategoryResponse> findAll(Long storeId);
}
