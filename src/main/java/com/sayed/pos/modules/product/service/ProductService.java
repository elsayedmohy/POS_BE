package com.sayed.pos.modules.product.service;

import com.sayed.pos.modules.product.dto.ProductRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import com.sayed.pos.modules.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<ProductResponse> findAllByStoreId(Long storeId);
    public ProductResponse findById(Long productId,Long storeId);
    public void deleteById(Long productId,Long storeId);
    public ProductResponse create(Long storeId, ProductRequest productRequest);
    public ProductResponse update(Long storeId, Long productId,ProductRequest productRequest);

}
