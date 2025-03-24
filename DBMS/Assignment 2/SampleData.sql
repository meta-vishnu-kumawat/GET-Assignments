
INSERT INTO Category (Name, ParentCategoryID) VALUES
('Electronics', NULL),         -- 1
('Fashion', NULL),             -- 2
('Home & Kitchen', NULL),      -- 3
('Mobile Phones', 1),          -- 4 (child of Electronics)
('Laptops', 1),                -- 5 (child of Electronics)
('Men''s Clothing', 2),        -- 6 (child of Fashion)
('Women''s Clothing', 2),      -- 7 (child of Fashion)
('Appliances', 3),             -- 8 (child of Home & Kitchen)
('Furniture', 3),              -- 9 (child of Home & Kitchen)
('Tablets', 1);                -- 10 (child of Electronics)


INSERT INTO Product (Name, Description, Price, StockQuantity) VALUES
('iPhone 14', 'Latest Apple smartphone', 799.99, 50),
('MacBook Pro', 'Apple high-performance laptop', 1999.99, 20),
('Galaxy S22', 'Samsung flagship smartphone', 699.99, 40),
('Dell XPS 13', 'Dell ultraportable laptop', 1099.99, 15),
('Sony WH-1000XM4', 'Noise cancelling headphones', 349.99, 30),
('Nike Running Shoes', 'Comfortable running shoes', 129.99, 60),
('Levi''s Jeans', 'Classic blue jeans', 59.99, 80),
('KitchenAid Mixer', 'Stand mixer for home kitchens', 399.99, 25),
('Ikea Sofa', 'Modern 3-seater sofa', 499.99, 10),
('iPad Pro', 'Apple tablet with M1 chip', 899.99, 35);


INSERT INTO ProductCategory (ProductID, CategoryID) VALUES
(1, 4),   -- iPhone 14 -> Mobile Phones
(1, 10),  -- iPhone 14 -> Tablets
(2, 5),   -- MacBook Pro -> Laptops
(3, 4),   -- Galaxy S22 -> Mobile Phones
(4, 5),   -- Dell XPS 13 -> Laptops
(5, 1),   -- Sony WH-1000XM4 -> Electronics
(6, 2),   -- Nike Running Shoes -> Fashion
(7, 6),   -- Levi's Jeans -> Men's Clothing
(8, 8),   -- KitchenAid Mixer -> Appliances
(9, 9);   -- Ikea Sofa -> Furniture


INSERT INTO Image (URL, ProductID) VALUES
('/images/iphone14_1.jpg', 1),
('/images/iphone14_2.jpg', 1),
('/images/macbookpro.jpg', 2),
('/images/galaxys22.jpg', 3),
('/images/dellxps13.jpg', 4),
('/images/sony_headphones.jpg', 5),
('/images/nike_shoes.jpg', 6),
('/images/levis_jeans.jpg', 7),
('/images/kitchenaid.jpg', 8),
('/images/ikea_sofa.jpg', 9);


INSERT INTO User (Username, Email, Password, Role) VALUES
('alice', 'alice@example.com', 'pass1', 'Shopper'),
('bob_admin', 'bob.admin@example.com', 'pass2', 'Administrator'),
('charlie', 'charlie@example.com', 'pass3', 'Shopper'),
('diana', 'diana@example.com', 'pass4', 'Shopper'),
('edward', 'edward@example.com', 'pass5', 'Shopper'),
('fiona', 'fiona@example.com', 'pass6', 'Shopper'),
('george', 'george@example.com', 'pass7', 'Shopper'),
('harriet', 'harriet@example.com', 'pass8', 'Shopper'),
('ivan_admin', 'ivan.admin@example.com', 'pass9', 'Administrator'),
('julia_admin', 'julia.admin@example.com', 'pass10', 'Administrator');



INSERT INTO ShippingAddress (ShopperID, Street, City, State, ZipCode, Country) VALUES
(1, '123 Main St', 'Springfield', 'IL', '62701', 'USA'),
(3, '456 Oak Ave', 'Riverside', 'CA', '92501', 'USA'),
(4, '789 Pine Rd', 'Austin', 'TX', '73301', 'USA'),
(5, '321 Maple Dr', 'Madison', 'WI', '53703', 'USA'),
(6, '654 Elm St', 'Denver', 'CO', '80201', 'USA'),
(7, '987 Cedar Ln', 'Orlando', 'FL', '32801', 'USA'),
(8, '135 Birch Blvd', 'Seattle', 'WA', '98101', 'USA'),
(1, '246 Walnut St', 'Springfield', 'IL', '62704', 'USA'),
(3, '369 Cherry Ave', 'Riverside', 'CA', '92503', 'USA'),
(4, '159 Poplar Rd', 'Austin', 'TX', '73344', 'USA');


INSERT INTO OrderTable (OrderDate, ShopperID) VALUES
('2025-03-20 10:00:00', 1),
('2025-03-21 11:00:00', 3),
('2025-03-22 12:00:00', 4),
('2025-03-23 13:00:00', 5),
('2025-03-24 14:00:00', 6),
('2025-03-25 15:00:00', 7),
('2025-03-26 16:00:00', 8),
('2025-03-27 17:00:00', 1),
('2025-03-28 18:00:00', 3),
('2025-03-29 19:00:00', 4);


INSERT INTO OrderItem (OrderID, ProductID, Quantity, Status) VALUES
(1, 1, 2, 'Shipped'),
(2, 2, 1, 'Shipped'),
(3, 3, 1, 'Canceled'),
(4, 4, 2, 'Shipped'),
(5, 5, 1, 'Returned'),
(6, 6, 3, 'Shipped'),
(7, 7, 1, 'Shipped'),
(8, 8, 1, 'Shipped'),
(9, 9, 2, 'Shipped'),
(10, 10, 1, 'Shipped');
