package com.sayed.pos.modules.sale.mapper;

import com.sayed.pos.modules.sale.dto.SaleItemResponse;
import com.sayed.pos.modules.sale.dto.SaleResponse;
import com.sayed.pos.modules.sale.entity.Sale;
import com.sayed.pos.modules.sale.entity.SaleItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(source = "items", target = "items")
    @Mapping(source = "user.name", target = "createdBy")
    SaleResponse toResponse(Sale sale);
    SaleItemResponse toItemResponse(SaleItem saleItem);
}
