package com.sayed.pos.modules.sale.dto;

import com.sayed.pos.modules.sale.entity.SaleItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleResponse {
    private Long id;
    private BigDecimal totalAmount;
    private List<SaleItemResponse> items;
}
