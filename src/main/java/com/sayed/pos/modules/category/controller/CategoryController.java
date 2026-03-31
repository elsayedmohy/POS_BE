package com.sayed.pos.modules.category.controller;

import com.sayed.pos.common.response.ApiResponse;
import com.sayed.pos.modules.category.dto.CategoryResponse;
import com.sayed.pos.modules.category.service.CategoryService;
import com.sayed.pos.security.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> findAllByStoreId(@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        return ResponseEntity.ok(ApiResponse.success(categoryService.findAll(storeId)));
    }
}
