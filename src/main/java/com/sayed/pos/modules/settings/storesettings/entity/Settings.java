package com.sayed.pos.modules.settings.storesettings.entity;

import com.sayed.pos.modules.store.entity.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "store_settings", schema = "pos_api")
public class Settings {
    @Id
    @Column(name = "store_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Size(max = 10)
    @ColumnDefault("'EGP'")
    @Column(name = "currency", length = 10)
    private String currency;

    @ColumnDefault("0.00")
    @Column(name = "tax_percentage", precision = 5, scale = 2)
    private BigDecimal taxPercentage;

    @ColumnDefault("0")
    @Column(name = "enable_taxes")
    private Boolean enableTaxes;

    @Size(max = 255)
    @Column(name = "logo_url")
    private String logoUrl;

    @Lob
    @Column(name = "address")
    private String address;

    @Size(max = 50)
    @Column(name = "phone", length = 50)
    private String phone;


}