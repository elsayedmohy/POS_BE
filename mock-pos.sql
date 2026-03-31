-- ===========================================
-- MOCK DATA FOR RETAIL POS SaaS
-- ===========================================

-- -------------------------------
-- 1. Stores
-- -------------------------------
INSERT INTO stores (name, owner_name, phone) VALUES
                                                 ('SuperMart Downtown', 'Ali Mohamed', '01012345601'),
                                                 ('QuickShop Nasr City', 'Sara Ahmed', '01012345602'),
                                                 ('MiniMarket Maadi', 'Omar Taha', '01012345603'),
                                                 ('FreshMart Heliopolis', 'Laila Samir', '01012345604'),
                                                 ('DailyNeeds Zamalek', 'Khaled Farid', '01012345605'),
                                                 ('CityShop Dokki', 'Mona Adel', '01012345606'),
                                                 ('EasyBuy Mohandessin', 'Hassan Fathy', '01012345607'),
                                                 ('GreenStore Maadi', 'Nourhan Sami', '01012345608'),
                                                 ('FoodPoint Nasr City', 'Mahmoud Nabil', '01012345609'),
                                                 ('QuickPick Heliopolis', 'Rania Khaled', '01012345610');

-- -------------------------------
-- 2. Settings
-- -------------------------------
INSERT INTO settings (store_id, currency, tax_percentage) VALUES
                                                              (1,'EGP',14),(2,'EGP',10),(3,'EGP',12),(4,'EGP',14),(5,'EGP',15),
                                                              (6,'EGP',10),(7,'EGP',13),(8,'EGP',14),(9,'EGP',12),(10,'EGP',10);

-- -------------------------------
-- 3. Categories
-- -------------------------------
INSERT INTO categories (id,store_id, name) VALUES
                                            (1,1, 'Beverages'),
                                            (2,1, 'Dairy Products'),
                                            (3,1, 'Bakery'),
                                            (4,1, 'Snacks'),
                                            (5,1, 'Frozen Foods'),
                                            (6,1, 'Fruits & Vegetables'),
                                            (7,1, 'Meat & Poultry'),
                                            (8,1, 'Seafood'),
                                            (9,1, 'Canned Food'),
                                            (10,1, 'Grains & Rice'),
                                            (11,1, 'Pasta & Noodles'),
                                            (12,1, 'Spices & Seasonings'),
                                            (13,1, 'Oils & Sauces'),
                                            (14,1, 'Sweets & Chocolate'),
                                            (15,1, 'Breakfast Items'),
                                            (16,1, 'Cleaning Supplies'),
                                            (17,1, 'Personal Care'),
                                            (18,1, 'Baby Products'),
                                            (19,1, 'Pet Supplies'),
                                            (20,1, 'Stationery'),
                                            (21,1, 'Tobacco'),
                                            (22,1, 'Health & Wellness');

-- -------------------------------
-- 4. Products
-- -------------------------------
-- 5 products per store
INSERT INTO products
(store_id, category_id, name, price, cost, stock, barcode, created_at, active, description) VALUES

-- Beverages
(1, 1, 'Coca Cola 1L', 15.00, 10.00, 100, '6223000000011', NOW(), 1, 'Soft drink'),
(1, 1, 'Pepsi Can 330ml', 8.00, 5.50, 150, '6223000000012', NOW(), 1, 'Carbonated drink'),
(1, 1, 'Mineral Water 1.5L', 7.00, 4.00, 200, '6223000000013', NOW(), 1, 'Drinking water'),

-- Dairy
(1, 2, 'Milk 1L', 25.00, 18.00, 80, '6223000000021', NOW(), 1, 'Fresh milk'),
(1, 2, 'Cheese 500g', 45.00, 30.00, 60, '6223000000022', NOW(), 1, 'White cheese'),
(1, 2, 'Yogurt Cup', 6.00, 3.50, 120, '6223000000023', NOW(), 1, 'Natural yogurt'),

-- Bakery
(1, 3, 'Baladi Bread', 2.00, 1.00, 300, '6223000000031', NOW(), 1, 'Traditional bread'),
(1, 3, 'Toast Bread', 20.00, 14.00, 90, '6223000000032', NOW(), 1, 'Sliced bread'),

-- Snacks
(1, 4, 'Chipsy Salt 50g', 5.00, 3.00, 200, '6223000000041', NOW(), 1, 'Potato chips'),
(1, 4, 'Doritos Nacho', 10.00, 6.50, 140, '6223000000042', NOW(), 1, 'Tortilla chips'),

-- Frozen
(1, 5, 'Frozen Chicken 1kg', 120.00, 95.00, 40, '6223000000051', NOW(), 1, 'Frozen poultry'),
(1, 5, 'Frozen Fries 1kg', 35.00, 25.00, 70, '6223000000052', NOW(), 1, 'Potato fries'),

-- Fruits & Vegetables
(1, 6, 'Tomatoes 1kg', 12.00, 8.00, 100, '6223000000061', NOW(), 1, 'Fresh tomatoes'),
(1, 6, 'Bananas 1kg', 18.00, 12.00, 90, '6223000000062', NOW(), 1, 'Fresh bananas'),

-- Meat
(1, 7, 'Beef 1kg', 280.00, 250.00, 25, '6223000000071', NOW(), 1, 'Fresh beef'),
(1, 7, 'Chicken Breast 1kg', 140.00, 110.00, 35, '6223000000072', NOW(), 1, 'Boneless chicken'),

-- Canned
(1, 9, 'Tuna Can', 25.00, 18.00, 130, '6223000000091', NOW(), 1, 'Canned tuna'),
(1, 9, 'Beans Can', 10.00, 6.00, 160, '6223000000092', NOW(), 1, 'Canned beans'),

-- Grocery
(1, 10, 'Rice 1kg', 30.00, 24.00, 110, '6223000000101', NOW(), 1, 'White rice'),
(1, 10, 'Sugar 1kg', 28.00, 22.00, 120, '6223000000102', NOW(), 1, 'Granulated sugar');
-- Similarly for stores 3–10 (you can repeat pattern or randomize)

-- -------------------------------
-- 5. Users
-- -------------------------------
-- 3 users per store: 1 Admin + 2 Cashiers
INSERT INTO users (store_id, name, email, password, role) VALUES
    (1,'Mohamed Ali','mohamed1@store.com','hashedpwd','ADMIN'),
    (1,'Ahmed Hossam','ahmed1@store.com','hashedpwd','CASHIER'),
    (1,'Sara Khaled','sara1@store.com','hashedpwd','CASHIER'),
    (2,'Sara Ahmed','sara2@store.com','hashedpwd','ADMIN'),
    (2,'Nour Fathy','nour2@store.com','hashedpwd','CASHIER'),
    (2,'Omar Taha','omar2@store.com','hashedpwd','CASHIER');
-- Repeat pattern for stores 3–10

-- -------------------------------
-- 6. Sales
-- -------------------------------
-- 2 sales per store
INSERT INTO sales (store_id, user_id, total_amount, payment_method, created_at) VALUES
                                                                                    (1,2,50.0,'CASH','2026-03-20 10:00:00'),
                                                                                    (1,3,35.0,'CARD','2026-03-20 11:30:00'),
                                                                                    (2,5,42.0,'CASH','2026-03-20 12:15:00'),
                                                                                    (2,6,60.0,'CARD','2026-03-20 13:00:00');

-- -------------------------------
-- 7. Sale Items
-- -------------------------------
INSERT INTO sale_items (sale_id, product_id, quantity, price) VALUES
                                                                  (1,1,2,10),(1,3,2,15),
                                                                  (2,2,3,9),(2,4,1,8),
                                                                  (3,6,4,7),(3,8,1,25),
                                                                  (4,7,2,7),(4,9,3,10);

-- -------------------------------
-- 8. Stock Movements
-- -------------------------------
INSERT INTO stock_movements (product_id, type, quantity, reason, created_at) VALUES
                                                                                 (1,'IN',50,'Initial Stock','2026-03-01 09:00:00'),
                                                                                 (2,'IN',60,'Initial Stock','2026-03-01 09:00:00'),
                                                                                 (3,'IN',80,'Initial Stock','2026-03-01 09:00:00'),
                                                                                 (4,'IN',70,'Initial Stock','2026-03-01 09:00:00'),
                                                                                 (5,'IN',40,'Initial Stock','2026-03-01 09:00:00'),
                                                                                 (1,'OUT',2,'Sold in sale 1','2026-03-20 10:00:00'),
                                                                                 (3,'OUT',2,'Sold in sale 1','2026-03-20 10:00:00');