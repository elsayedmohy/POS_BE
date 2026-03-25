package com.sayed.pos.modules.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TopProductReportResponse {
    private Long productId;
    private String productName;
    private Long totalQuantity;
}
