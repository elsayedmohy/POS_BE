package com.sayed.pos.modules.settings.receiptsettings.entity;

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
@Table(name = "store_receipt_settings", schema = "pos_api")
public class ReceiptSetting {
    @Id
    @Column(name = "store_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ColumnDefault("1")
    @Column(name = "show_logo")
    private Boolean showLogo;

    @ColumnDefault("1")
    @Column(name = "show_address")
    private Boolean showAddress;

    @Size(max = 255)
    @Column(name = "footer_message")
    private String footerMessage;


}