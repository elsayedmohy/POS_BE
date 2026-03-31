package com.sayed.pos.modules.product.service;


import com.sayed.pos.common.exception.ResourceNotFoundException;
import com.sayed.pos.modules.category.repository.CategoryRepository;
import com.sayed.pos.modules.product.dto.ProductRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import com.sayed.pos.modules.product.entity.Product;
import com.sayed.pos.modules.product.mapper.ProductMapper;
import com.sayed.pos.modules.product.repository.ProductRepository;
import com.sayed.pos.modules.store.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @Override
    public List<ProductResponse> findAllByStoreId(Long storeId) {
        return productRepository.findAllByStoreId(storeId)
                .stream()
                .map(productMapper::toResponseDto).toList();
    }

    @Override
    public ProductResponse findById(Long productId,Long storeId)  {
       var  product = productRepository.findByIdAndStoreId(productId,storeId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.toResponseDto(product);
    }

    @Override
    public void deleteById(Long productId ,Long storeId) {
        var  product = productRepository.findByIdAndStoreId(productId,storeId).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    @Override
    public ProductResponse create(Long storeId , ProductRequest request) {
        var category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        var store = storeRepository.findById(storeId).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        var product = productMapper.toEntity(request);
        product.setCategory(category);
        product.setStore(store);
        productRepository.save(product);
        return  productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponse update(Long storeId , Long productId ,ProductRequest request) {
        var product = productRepository.findByIdAndStoreId(productId,storeId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setName(request.getName());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        product.setBarcode(request.getBarcode());
        product.setDescription(request.getDescription());
        product.setActive(request.isActive());
        product.setBarcode(request.getBarcode());
        if(!product.getCategory().getId().equals(request.getCategoryId())) {
            var category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }
        var updated = productRepository.save(product);

        return  productMapper.toResponseDto(updated);
    }

}
