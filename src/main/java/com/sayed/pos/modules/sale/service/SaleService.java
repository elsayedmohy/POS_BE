package com.sayed.pos.modules.sale.service;

import com.sayed.pos.modules.sale.dto.SaleRequest;
import com.sayed.pos.modules.sale.dto.SaleResponse;
import com.sayed.pos.security.AuthUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public interface SaleService {
    SaleResponse createSale(Long userId , Long storeId, SaleRequest request);
}
