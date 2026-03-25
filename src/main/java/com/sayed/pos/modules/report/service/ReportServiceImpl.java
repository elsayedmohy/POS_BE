package com.sayed.pos.modules.report.service;

import com.sayed.pos.common.exception.BadRequestException;
import com.sayed.pos.modules.product.repository.ProductRepository;
import com.sayed.pos.modules.report.dto.DashboardResponse;
import com.sayed.pos.modules.report.dto.TopProductReportResponse;
import com.sayed.pos.modules.report.mapper.ReportMapper;
import com.sayed.pos.modules.sale.repository.SaleItemRepository;
import com.sayed.pos.modules.sale.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final ReportMapper reportMapper;
    private final SaleItemRepository saleItemRepository;

    @Override
    public BigDecimal generateSalesSumReport( Long storeId, Instant from, Instant to) {
        if (from.isAfter(to)) {
            throw new BadRequestException("Invalid date range");
        }
        return saleRepository.findSalesSum(storeId, from , to).orElse(BigDecimal.ZERO);
    }

    @Override
    public int generateSalesCountReport(Long storeId, Instant from, Instant to) {
        return saleRepository.findSalesCount(storeId , from , to).orElse(0);
    }

    @Override
    public List<TopProductReportResponse> generateTopProductsReport(Long storeId, Instant from, Instant to ,Integer limit) {
        return saleItemRepository.findTopProducts(storeId  , from , to ,limit);
    }

    @Override
    public DashboardResponse generateDashboardReport(Long storeId, Instant from, Instant to , Integer limit) {
        BigDecimal totalSales = generateSalesSumReport(storeId, from, to);
        int totalOrders = generateSalesCountReport(storeId, from, to);
        BigDecimal averageOrderValue = totalOrders == 0 ? BigDecimal.ZERO :
                totalSales.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
        List<TopProductReportResponse> topProducts = generateTopProductsReport(storeId, from, to , limit);
        return new DashboardResponse(
                totalSales,
                totalOrders,
                averageOrderValue,
                topProducts
        );
    }
}
