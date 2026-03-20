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
INSERT INTO categories (store_id, name) VALUES
-- For all stores
(1,'Beverages'),(1,'Snacks'),(1,'Dairy'),
(2,'Beverages'),(2,'Snacks'),(2,'Dairy'),
(3,'Beverages'),(3,'Snacks'),(3,'Dairy'),
(4,'Beverages'),(4,'Snacks'),(4,'Dairy'),
(5,'Beverages'),(5,'Snacks'),(5,'Dairy'),
(6,'Beverages'),(6,'Snacks'),(6,'Dairy'),
(7,'Beverages'),(7,'Snacks'),(7,'Dairy'),
(8,'Beverages'),(8,'Snacks'),(8,'Dairy'),
(9,'Beverages'),(9,'Snacks'),(9,'Dairy'),
(10,'Beverages'),(10,'Snacks'),(10,'Dairy');

-- -------------------------------
-- 4. Products
-- -------------------------------
-- 5 products per store
INSERT INTO products (store_id, category_id, name, price, stock, barcode) VALUES
-- Store 1
(1,1,'Coca Cola 500ml',10,50,'100000000001'),
(1,1,'Pepsi 500ml',9,60,'100000000002'),
(1,2,'Lays Chips',15,80,'100000000003'),
(1,2,'Doritos',20,70,'100000000004'),
(1,3,'Milk 1L',12,40,'100000000005'),
-- Store 2
(2,4,'Coca Cola 330ml',8,50,'100000000006'),
(2,4,'Pepsi 330ml',7,60,'100000000007'),
(2,5,'Pringles',25,30,'100000000008'),
(2,5,'KitKat',10,100,'100000000009'),
(2,6,'Cheese 200g',18,50,'100000000010');
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