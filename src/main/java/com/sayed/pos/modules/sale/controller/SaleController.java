package com.sayed.pos.modules.sale.controller;

import com.sayed.pos.common.response.ApiResponse;
import com.sayed.pos.modules.product.dto.ProductRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import com.sayed.pos.modules.sale.dto.SaleResponse;
import com.sayed.pos.modules.sale.dto.SaleRequest;
import com.sayed.pos.modules.sale.dto.SaleResponse;
import com.sayed.pos.modules.sale.service.SaleService;
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
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<SaleResponse>>> findAllByStoreId(@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        return ResponseEntity.ok(ApiResponse.success(saleService.findAllByStoreId(storeId)));
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<ApiResponse<SaleResponse>> findById(@PathVariable Long saleId ,@AuthenticationPrincipal AuthUser user) {
        Long storeId = user.getStoreId();
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    saleService.findById(saleId,storeId) , "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SaleResponse>> create(@AuthenticationPrincipal AuthUser user, @RequestBody SaleRequest request) {

        try {
            SaleResponse response = saleService.create(user.getUserId(), user.getStoreId(), request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(response, "تم انشاء الفاتورة بنجاح"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<ApiResponse<SaleResponse>> update(@AuthenticationPrincipal AuthUser user,
                                                               @PathVariable Long saleId,
                                                               @Valid @RequestBody SaleRequest request) {
        Long storeId = user.getStoreId();
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    saleService.update(storeId,saleId,request) , "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }


    @DeleteMapping("/{saleId}")
    public ResponseEntity<ApiResponse<?>> deleteById(@PathVariable Long saleId ,@AuthenticationPrincipal AuthUser user) {
        try {
            saleService.deleteById(saleId , user.getStoreId());

            return ResponseEntity.ok(ApiResponse.success(null , String.valueOf(HttpStatus.NO_CONTENT)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }

    }
}
