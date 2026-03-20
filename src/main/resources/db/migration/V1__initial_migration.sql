CREATE TABLE stores (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        owner_name VARCHAR(255),
                        phone VARCHAR(50),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       store_id BIGINT NOT NULL,
                       name VARCHAR(255),
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       role ENUM('ADMIN', 'CASHIER') DEFAULT 'CASHIER',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       FOREIGN KEY (store_id) REFERENCES stores(id)
);

CREATE TABLE categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            store_id BIGINT NOT NULL,
                            name VARCHAR(255) NOT NULL,

                            FOREIGN KEY (store_id) REFERENCES stores(id)
);

CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          store_id BIGINT NOT NULL,
                          category_id BIGINT,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(10,2) NOT NULL,
                          cost DECIMAL(10,2),
                          stock INT DEFAULT 0,
                          barcode VARCHAR(100),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          FOREIGN KEY (store_id) REFERENCES stores(id),
                          FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE sales (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       store_id BIGINT NOT NULL,
                       user_id BIGINT NOT NULL,
                       total_amount DECIMAL(10,2) NOT NULL,
                       payment_method ENUM('CASH', 'CARD') DEFAULT 'CASH',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       FOREIGN KEY (store_id) REFERENCES stores(id),
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE sale_items (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            sale_id BIGINT NOT NULL,
                            product_id BIGINT NOT NULL,
                            quantity INT NOT NULL,
                            price DECIMAL(10,2) NOT NULL,

                            FOREIGN KEY (sale_id) REFERENCES sales(id),
                            FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE stock_movements (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 product_id BIGINT NOT NULL,
                                 type ENUM('IN', 'OUT'),
                                 quantity INT NOT NULL,
                                 reason VARCHAR(255),
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                 FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE settings (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          store_id BIGINT NOT NULL,
                          currency VARCHAR(10) DEFAULT 'EGP',
                          tax_percentage DECIMAL(5,2) DEFAULT 0,

                          FOREIGN KEY (store_id) REFERENCES stores(id)
);