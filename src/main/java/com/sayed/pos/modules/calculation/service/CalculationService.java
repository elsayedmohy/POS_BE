package com.sayed.pos.modules.calculation.service;

import com.sayed.pos.modules.calculation.dto.CalculationResult;
import com.sayed.pos.modules.calculation.dto.SaleContext;

public interface CalculationService {
    CalculationResult calculate(SaleContext context);
}
