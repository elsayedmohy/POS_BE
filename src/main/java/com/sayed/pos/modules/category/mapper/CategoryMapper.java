package com.sayed.pos.modules.category.mapper;


import com.sayed.pos.modules.category.entity.Category;
import com.sayed.pos.modules.category.dto.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponseDto(Category category);
}
