package com.sayed.pos.modules.product.service;

import com.sayed.pos.modules.product.dto.ProductRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductResponse> findAllByStoreId(Long storeId);

    ProductResponse findById(Long productId, Long storeId);

    void deleteById(Long productId, Long storeId);

    ProductResponse create(Long storeId, ProductRequest productRequest);

    ProductResponse update(Long storeId, Long productId, ProductRequest productRequest);

}
