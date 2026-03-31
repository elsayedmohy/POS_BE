package com.sayed.pos.modules.report.service;

import com.sayed.pos.modules.report.dto.DashboardResponse;
import com.sayed.pos.modules.report.dto.TopProductReportResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public interface ReportService {
    BigDecimal generateTotalSalesReport(Long storeId, Instant from, Instant to);
    int generateTotalUsers(Long storeId);
    int generateSalesCountReport(Long storeId, Instant from, Instant to);
    List<TopProductReportResponse> generateTopProductsReport(Long storeId, Instant from, Instant to  ,Integer limit);
    DashboardResponse generateDashboardReport(Long storeId, Instant from, Instant to , Integer limit );
}
