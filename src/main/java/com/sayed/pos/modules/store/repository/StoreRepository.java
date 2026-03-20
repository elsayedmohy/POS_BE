package com.sayed.pos.modules.store.repository;

import com.sayed.pos.modules.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
