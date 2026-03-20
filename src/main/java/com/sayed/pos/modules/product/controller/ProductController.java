package com.sayed.pos.modules.product.controller;


import com.sayed.pos.modules.product.dto.ProductRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import com.sayed.pos.modules.product.service.ProductService;
import com.sayed.pos.security.AuthUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> findAllByStoreId(@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        return ResponseEntity.ok(productService.findAllByStoreId(storeId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long productId ,@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        return ResponseEntity.ok(productService.findById(productId,storeId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteById(@PathVariable Long productId ,@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        productService.deleteById(productId , storeId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<ProductResponse> create(@AuthenticationPrincipal AuthUser user, @Valid @RequestBody ProductRequest request) {
        Long storeId = user.getStoreId();
        var productResponse = productService.create(storeId,request);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("")
    public ResponseEntity<ProductResponse> update(@AuthenticationPrincipal AuthUser user,@RequestParam Long productId, @Valid @RequestBody ProductRequest request) {
        Long storeId = user.getStoreId();
        var productResponse = productService.update(storeId,productId,request);
        return ResponseEntity.ok(productResponse);
    }
}
