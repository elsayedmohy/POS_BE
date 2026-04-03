package com.sayed.pos.modules.sale.repository;

import com.sayed.pos.modules.product.entity.Product;
import com.sayed.pos.modules.sale.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select s from Sale s JOIN FETCH s.items where s.store.id = :storeId")
    List<Sale> findAllByStoreId (Long storeId);

    @Query("select s from Sale s JOIN FETCH s.items where  s.id = :saleId and s.store.id = :storeId")
    Optional<Sale> findByIdAndStoreId (Long saleId,Long storeId);

    @Query("SELECT s FROM Sale s JOIN FETCH s.items WHERE s.id = :id")
    Optional<Sale> findByIdWithItems(@Param("id") Long id);

    @Query("SELECT COUNT(s) FROM Sale s WHERE s.store.id = :storeId AND s.createdAt BETWEEN :from AND :to")
    Optional<Integer> findSalesCount(@Param("storeId") Long storeId , @Param("from") Instant from, @Param("to") Instant to);

    @Query("""
            SELECT SUM(s.totalAmount)
            FROM Sale s
            WHERE s.store.id = :storeId
            AND s.createdAt BETWEEN :from AND :to
            """)
    Optional<BigDecimal> findTotalSales( @Param("storeId") Long storeId , @Param("from") Instant from, @Param("to") Instant to);
}
