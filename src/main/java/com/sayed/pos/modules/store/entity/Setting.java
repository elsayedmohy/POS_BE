package com.sayed.pos.modules.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "settings")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Size(max = 10)
    @ColumnDefault("'EGP'")
    @Column(name = "currency", length = 10)
    private String currency;

    @ColumnDefault("0.00")
    @Column(name = "tax_percentage", precision = 5, scale = 2)
    private BigDecimal taxPercentage;


}