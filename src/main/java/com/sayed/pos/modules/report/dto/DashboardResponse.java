package com.sayed.pos.modules.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
public class DashboardResponse {
    private BigDecimal totalRevenue;
    private int totalSales;
    private int totalUsers;
    private BigDecimal averageOrderValue;
    private List<TopProductReportResponse> topProducts;
}
