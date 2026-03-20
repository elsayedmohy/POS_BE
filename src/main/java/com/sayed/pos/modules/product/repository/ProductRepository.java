package com.sayed.pos.modules.product.repository;

import com.sayed.pos.modules.product.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.store.id = :storeId")
    List<Product> findAllByStoreId (Long storeId);

    @Query("select p from Product p where  p.id = :productId and p.store.id = :storeId")
    Optional<Product> findByIdAndStoreId (Long productId,Long storeId);
}
