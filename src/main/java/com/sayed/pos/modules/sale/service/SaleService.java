package com.sayed.pos.modules.sale.service;

import com.sayed.pos.modules.sale.dto.SaleRequest;
import com.sayed.pos.modules.product.dto.ProductResponse;
import com.sayed.pos.modules.sale.dto.SaleRequest;
import com.sayed.pos.modules.sale.dto.SaleResponse;
import com.sayed.pos.security.AuthUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService {
    List<SaleResponse> findAllByStoreId(Long storeId);

    SaleResponse findById(Long saleId, Long storeId);
    SaleResponse create(Long userId , Long storeId, SaleRequest request);
    SaleResponse update(Long storeId, Long saleId, SaleRequest request);
    void deleteById(Long saleId, Long storeId);

}
