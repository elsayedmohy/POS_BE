package com.sayed.pos.modules.settings.storesettings.repository;

import com.sayed.pos.modules.settings.storesettings.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    @Query("select s from Settings s where s.store.id = :storeId")
    Optional<Settings> findAllByStoreId (Long storeId);
}
