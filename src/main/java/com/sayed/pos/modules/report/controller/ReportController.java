package com.sayed.pos.modules.report.controller;


import com.sayed.pos.common.exception.BadRequestException;
import com.sayed.pos.common.util.DateUtils;
import com.sayed.pos.modules.report.dto.DashboardResponse;
import com.sayed.pos.modules.report.dto.TopProductReportResponse;
import com.sayed.pos.modules.report.service.ReportService;
import com.sayed.pos.security.AuthUser;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("reports")
public class ReportController {


    private final ReportService reportService;
    private final DateUtils dateUtils;

    @GetMapping("/sales/total")
    public ResponseEntity<Map<String,BigDecimal>> getSalesSum(@AuthenticationPrincipal AuthUser user,
                                           @RequestParam
                                                  @NotNull
                                                  LocalDate from,
                                           @RequestParam
                                                  @NotNull
                                                  LocalDate to) {
        Instant fromInstant = DateUtils.toStartOfDay(from);
        Instant toInstant = DateUtils.toEndOfDay(to);
        DateUtils.validateDateRange(from, to);
        return ResponseEntity.ok(Map.of(
                "totalSales" , reportService.generateSalesSumReport(user.getStoreId(), fromInstant, toInstant)
        ));
    }


    @GetMapping("/sales/count")
    public ResponseEntity<Integer> getSalesCount(@AuthenticationPrincipal AuthUser user,
                                                 @RequestParam
                                                 @NotNull
                                                 LocalDate from,
                                                 @RequestParam
                                                 @NotNull
                                                 LocalDate to) {
        Instant fromInstant = DateUtils.toStartOfDay(from);
        Instant toInstant = DateUtils.toEndOfDay(to);
        DateUtils.validateDateRange(from, to);

        return ResponseEntity.ok(reportService.generateSalesCountReport(user.getStoreId(), fromInstant, toInstant));
    }

    @GetMapping("/products/top")
    public ResponseEntity<List<TopProductReportResponse>> getTopProduct(
            @AuthenticationPrincipal AuthUser user,
            @RequestParam
            @NotNull
            LocalDate from,
            @RequestParam
            @NotNull
            LocalDate to,
            @RequestParam
            @Min(1)
            @Max(100)
            Integer limit) {
        Instant fromInstant = DateUtils.toStartOfDay(from);
        Instant toInstant = DateUtils.toEndOfDay(to);
        DateUtils.validateDateRange(from, to);
        return ResponseEntity.ok(
                reportService.generateTopProductsReport(
                        user.getStoreId(), fromInstant, toInstant, limit
                )
        );
    }


    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> dashboard(
            @AuthenticationPrincipal AuthUser user,
            @RequestParam
            @NotNull
            LocalDate from,
            @RequestParam
            @NotNull
            LocalDate to,
            @RequestParam(required = false)
                    @Min(1)
                    @Max(100)
            Integer limit) {
        if (limit <= 0) {
            throw new BadRequestException("Limit must be positive");
        }
        Instant fromInstant = DateUtils.toStartOfDay(from);
        Instant toInstant = DateUtils.toEndOfDay(to);
        DateUtils.validateDateRange(from, to);
        return ResponseEntity.ok(
                reportService.generateDashboardReport(user.getStoreId(), fromInstant, toInstant ,limit));
    }

}
