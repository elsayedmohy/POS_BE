package com.sayed.pos.modules.calculation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CalculationItem {
    private BigDecimal price;
    private int quantity;
}
