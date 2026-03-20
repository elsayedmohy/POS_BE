package com.sayed.pos.modules.product.controller;


import com.sayed.pos.modules.product.dto.ProductRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import com.sayed.pos.modules.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> findAllByStoreId(@RequestParam Long storeId) {
        return ResponseEntity.ok(productService.findAllByStoreId(storeId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long productId ,@RequestParam Long storeId) {
        return ResponseEntity.ok(productService.findById(productId,storeId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteById(@PathVariable Long productId ,@RequestParam Long storeId) {
        productService.deleteById(productId , storeId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<ProductResponse> create(@RequestParam Long storeId, @Valid @RequestBody ProductRequest request) {
        var productResponse = productService.create(storeId,request);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("")
    public ResponseEntity<ProductResponse> update(@RequestParam Long storeId,@RequestParam Long productId, @Valid @RequestBody ProductRequest request) {
        var productResponse = productService.update(storeId,productId,request);
        return ResponseEntity.ok(productResponse);
    }
}
