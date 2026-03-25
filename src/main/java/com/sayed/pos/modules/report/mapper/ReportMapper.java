package com.sayed.pos.modules.report.mapper;

import com.sayed.pos.modules.product.entity.Product;
import com.sayed.pos.modules.report.dto.TopProductReportResponse;
import com.sayed.pos.modules.sale.entity.SaleItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    TopProductReportResponse toTopProduct(SaleItem item);

}
