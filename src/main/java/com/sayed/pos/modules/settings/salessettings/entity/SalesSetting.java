package com.sayed.pos.modules.settings.salessettings.entity;

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
@Table(name = "store_sales_settings", schema = "pos_api")
public class SalesSetting {
    @Id
    @Column(name = "store_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ColumnDefault("0")
    @Column(name = "allow_negative_stock")
    private Boolean allowNegativeStock;

    @Size(max = 20)
    @Column(name = "default_payment_method", length = 20)
    private String defaultPaymentMethod;


}