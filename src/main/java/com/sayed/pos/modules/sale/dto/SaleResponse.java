package com.sayed.pos.modules.sale.dto;

import com.sayed.pos.common.dto.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class SaleResponse {
    private Long id;
    private BigDecimal totalAmount;
    private List<SaleItemResponse> items;
    private Instant createdAt;
    private String createdBy;
    private String customerName;
    private PaymentMethod paymentMethod;

}
