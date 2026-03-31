package com.sayed.pos.modules.report.service;

import com.sayed.pos.common.exception.BadRequestException;
import com.sayed.pos.modules.product.repository.ProductRepository;
import com.sayed.pos.modules.report.dto.DashboardResponse;
import com.sayed.pos.modules.report.dto.TopProductReportResponse;
import com.sayed.pos.modules.report.mapper.ReportMapper;
import com.sayed.pos.modules.sale.repository.SaleItemRepository;
import com.sayed.pos.modules.sale.repository.SaleRepository;
import com.sayed.pos.modules.user.entity.User;
import com.sayed.pos.modules.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final ReportMapper reportMapper;
    private final SaleItemRepository saleItemRepository;
    private final UserRepository userRepository;

    @Override
    public BigDecimal generateTotalSalesReport( Long storeId, Instant from, Instant to) {
        if (from.isAfter(to)) {
            throw new BadRequestException("Invalid date range");
        }
        return saleRepository.findTotalSales(storeId, from , to).orElse(BigDecimal.ZERO);
    }


    @Override
    public int generateTotalUsers(Long storeId) {
        List<User> users =  userRepository.findAllByStoreId(storeId);
        if (users == null) {
            throw new BadRequestException("Users not found");
        }
        return users.size();
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
        BigDecimal totalRevenue = generateTotalSalesReport(storeId, from, to);
        int totalSales = generateSalesCountReport(storeId, from, to);
        int totalUsers = generateTotalUsers(storeId);
        BigDecimal averageOrderValue = totalSales == 0 ? BigDecimal.ZERO :
                totalRevenue.divide(BigDecimal.valueOf(totalSales), 2, RoundingMode.HALF_UP);
        List<TopProductReportResponse> topProducts = generateTopProductsReport(storeId, from, to , limit);
        return new DashboardResponse(
                totalRevenue,
                totalSales,
                totalUsers,
                averageOrderValue,
                topProducts
        );
    }
}
