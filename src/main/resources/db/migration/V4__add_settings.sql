CREATE TABLE store_settings (
                                store_id BIGINT PRIMARY KEY,

                                currency VARCHAR(10) DEFAULT 'EGP',
                                tax_percentage DECIMAL(5,2) DEFAULT 0,
                                enable_taxes BOOLEAN DEFAULT FALSE,

                                logo_url VARCHAR(255),
                                address TEXT,
                                phone VARCHAR(50),

                                FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE
);


CREATE TABLE store_receipt_settings (
                                        store_id BIGINT PRIMARY KEY,

                                        show_logo BOOLEAN DEFAULT TRUE,
                                        show_address BOOLEAN DEFAULT TRUE,
                                        footer_message VARCHAR(255),

                                        FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE
);


CREATE TABLE store_localization_settings (
                                             store_id BIGINT PRIMARY KEY,

                                             language VARCHAR(10) DEFAULT 'ar',
                                             timezone VARCHAR(50) DEFAULT 'Africa/Cairo',
                                             date_format VARCHAR(20) DEFAULT 'dd/MM/yyyy',

                                             FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE
);


CREATE TABLE store_sales_settings (
                                      store_id BIGINT PRIMARY KEY,

                                      allow_negative_stock BOOLEAN DEFAULT FALSE,
                                      default_payment_method VARCHAR(20),

                                      FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE
);