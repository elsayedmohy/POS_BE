package com.sayed.pos.modules.product.mapper;


import com.sayed.pos.modules.product.dto.ProductRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import com.sayed.pos.modules.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name" ,target = "categoryName")
    ProductResponse toResponseDto(Product product);
    Product toEntity(ProductRequest productRequest);
}
