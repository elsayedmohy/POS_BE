package com.sayed.pos.modules.sale.controller;

import com.sayed.pos.common.response.ApiResponse;
import com.sayed.pos.modules.sale.dto.SaleRequest;
import com.sayed.pos.modules.sale.dto.SaleResponse;
import com.sayed.pos.modules.sale.service.SaleService;
import com.sayed.pos.security.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;


    @PostMapping
    public ResponseEntity<ApiResponse<SaleResponse>> save(@AuthenticationPrincipal AuthUser user, @RequestBody SaleRequest request) {

        try {
            SaleResponse response = saleService.createSale(user.getUserId(), user.getStoreId(), request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(response, "تم انشاء الفاتورة بنجاح"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
