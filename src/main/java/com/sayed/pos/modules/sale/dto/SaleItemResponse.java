package com.sayed.pos.modules.sale.dto;

import com.sayed.pos.modules.product.dto.ProductResponse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleItemResponse {
    private Long id;
    private int quantity;
    private BigDecimal price;
}
