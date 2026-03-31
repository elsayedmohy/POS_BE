package com.sayed.pos.modules.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long categoryId;
    private String categoryName;
    private String barcode;
    private int stock;
    private boolean active;
    private String description;
}
