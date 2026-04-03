package com.sayed.pos.modules.sale.dto;

import com.sayed.pos.common.dto.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleRequest {
    private List<SaleItemRequest> items;
    private PaymentMethod paymentMethod;
    private String discountType;
    private BigDecimal discountValue;
    private String customerName;
}
