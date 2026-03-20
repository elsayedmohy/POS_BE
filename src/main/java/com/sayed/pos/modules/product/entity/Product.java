package com.sayed.pos.modules.product.entity;

import com.sayed.pos.modules.category.entity.Category;
import com.sayed.pos.modules.sale.entity.SaleItem;
import com.sayed.pos.modules.store.entity.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    
    
    @Column(name = "name")
    private String name;

    
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "cost", precision = 10, scale = 2)
    private BigDecimal cost;

    @ColumnDefault("0")
    @Column(name = "stock")
    private Integer stock;

    @Size(max = 100)
    @Column(name = "barcode", length = 100)
    private String barcode;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "product")
    private Set<SaleItem> saleItems = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<StockMovement> stockMovements = new LinkedHashSet<>();


}