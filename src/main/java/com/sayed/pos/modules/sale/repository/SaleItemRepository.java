package com.sayed.pos.modules.sale.repository;


import com.sayed.pos.modules.sale.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository  extends JpaRepository<SaleItem,Long> {
}
