package com.sayed.pos.modules.calculation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
public class SaleContext {
    private List<CalculationItem> items;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal taxRate;
    private boolean enableTaxes;
}
