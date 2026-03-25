package com.sayed.pos.modules.sale.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaleRequest {
    private List<SaleItemRequest> items;
    private String paymentMethod;
}
