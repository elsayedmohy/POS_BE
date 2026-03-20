package com.sayed.pos.modules.product.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private int stock;
    private Long categoryId;
    @Size(min = 1, max = 100)
    private String barcode;
}
