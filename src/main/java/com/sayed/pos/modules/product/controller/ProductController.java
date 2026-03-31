package com.sayed.pos.modules.product.controller;


import com.sayed.pos.common.response.ApiResponse;
import com.sayed.pos.modules.product.dto.ProductRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import com.sayed.pos.modules.product.service.ProductService;
import com.sayed.pos.security.AuthUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> findAllByStoreId(@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        return ResponseEntity.ok(ApiResponse.success(productService.findAllByStoreId(storeId)));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> findById(@PathVariable Long productId ,@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        try {
            return ResponseEntity.ok(ApiResponse.success(
                   productService.findById(productId,storeId) , "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<?>> deleteById(@PathVariable Long productId ,@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        try {
            productService.deleteById(productId , storeId);

            return ResponseEntity.ok(ApiResponse.success(null , String.valueOf(HttpStatus.NO_CONTENT)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }

    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<ProductResponse>> create(@AuthenticationPrincipal AuthUser user, @Valid @RequestBody ProductRequest request) {
        Long storeId = user.getStoreId();
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    productService.create(storeId,request) , "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@AuthenticationPrincipal AuthUser user,@PathVariable Long productId, @Valid @RequestBody ProductRequest request) {
        Long storeId = user.getStoreId();
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    productService.update(storeId,productId,request) , "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
