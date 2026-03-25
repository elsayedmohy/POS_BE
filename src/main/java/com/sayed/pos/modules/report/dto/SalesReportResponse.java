package com.sayed.pos.modules.report.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalesReportResponse {
    private BigDecimal totalSales;
    private Long totalOrders;
}
