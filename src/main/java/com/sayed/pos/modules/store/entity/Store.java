package com.sayed.pos.modules.store.entity;

import com.sayed.pos.modules.category.entity.Category;
import com.sayed.pos.modules.product.entity.Product;
import com.sayed.pos.modules.sale.entity.Sale;
import com.sayed.pos.modules.user.entity.User;
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
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    
    
    @Column(name = "name")
    private String name;

    
    @Column(name = "owner_name")
    private String ownerName;

    @Size(max = 50)
    @Column(name = "phone", length = 50)
    private String phone;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "store")
    private Set<Category> categories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Product> products = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Sale> sales = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Setting> settings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<User> users = new LinkedHashSet<>();


}