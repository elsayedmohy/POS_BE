package com.sayed.pos.modules.settings.localizationsettings.entity;

import com.sayed.pos.modules.store.entity.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "store_localization_settings", schema = "pos_api")
public class LocalizationSetting {
    @Id
    @Column(name = "store_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Size(max = 10)
    @ColumnDefault("'ar'")
    @Column(name = "language", length = 10)
    private String language;

    @Size(max = 50)
    @ColumnDefault("'Africa/Cairo'")
    @Column(name = "timezone", length = 50)
    private String timezone;

    @Size(max = 20)
    @ColumnDefault("'dd/MM/yyyy'")
    @Column(name = "date_format", length = 20)
    private String dateFormat;


}