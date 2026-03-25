package com.sayed.pos.modules.sale.repository;


import com.sayed.pos.modules.report.dto.TopProductReportResponse;
import com.sayed.pos.modules.sale.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    @Query("""
SELECT new com.sayed.pos.modules.report.dto.TopProductReportResponse(
    p.id,
    p.name,
    SUM(si.quantity)
)
FROM SaleItem si
JOIN si.product p
WHERE p.createdAt BETWEEN :from and :to
GROUP BY p.id, p.name
ORDER BY SUM(si.quantity) DESC
LIMIT :limit
""")
    List<TopProductReportResponse> findTopProducts(@Param("storeId") Long storeId , @Param("from") Instant from, @Param("to") Instant to , @Param("limit") Integer limit);
}
