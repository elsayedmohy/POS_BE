package com.sayed.pos.modules.user.entity;

import com.sayed.pos.modules.sale.entity.Sale;
import com.sayed.pos.modules.store.entity.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    
    @Column(name = "name")
    private String name;

    
    @Column(name = "email")
    private String email;

    
    @Column(name = "password")
    private String password;

    @ColumnDefault("'CASHIER'")
    @Lob
    @Column(name = "role")
    private String role;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "user")
    private Set<Sale> sales = new LinkedHashSet<>();


}