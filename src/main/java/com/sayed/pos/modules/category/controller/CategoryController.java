package com.sayed.pos.modules.category.controller;

import com.sayed.pos.common.response.ApiResponse;
import com.sayed.pos.modules.category.dto.CategoryRequest;
import com.sayed.pos.modules.category.dto.CategoryResponse;
import com.sayed.pos.modules.category.service.CategoryService;
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
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> findAllByStoreId(@AuthenticationPrincipal AuthUser user) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.findAll(user.getStoreId())));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> findById(@PathVariable Long categoryId , @AuthenticationPrincipal AuthUser user) {
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    categoryService.findById(categoryId,user.getStoreId()) , "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<?>> deleteById(@PathVariable Long categoryId ,@AuthenticationPrincipal AuthUser user) {
        try {
            categoryService.deleteById(categoryId , user.getStoreId());

            return ResponseEntity.ok(ApiResponse.success(null , String.valueOf(HttpStatus.NO_CONTENT)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }

    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@AuthenticationPrincipal AuthUser user, @Valid @RequestBody CategoryRequest request) {
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    categoryService.create(user.getStoreId(),request) , "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@AuthenticationPrincipal AuthUser user,@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest request) {
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    categoryService.update(user.getStoreId(),categoryId,request) , "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
