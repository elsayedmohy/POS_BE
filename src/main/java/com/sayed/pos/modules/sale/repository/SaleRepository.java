package com.sayed.pos.modules.sale.repository;

import com.sayed.pos.modules.sale.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s JOIN FETCH s.items WHERE s.id = :id")
    Optional<Sale> findByIdWithItems(@Param("id") Long id);
}
