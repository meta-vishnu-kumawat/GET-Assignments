
USE Storefront;


INSERT INTO Category (Name, ParentCategoryID) VALUES
('Electronics', NULL),           -- 1
('Mobiles', 1),                  -- 2
('Laptops', 1),                  -- 3
('Cameras', 1),                  -- 4
('Home Appliances', NULL),       -- 5
('Kitchen', 5),                  -- 6
('Furniture', NULL),             -- 7
('Books', NULL),                 -- 8
('Clothing', NULL),              -- 9
('Sports', NULL),                -- 10
('Toys', NULL),                  -- 11
('Groceries', NULL),             -- 12
('Health', NULL),                -- 13
('Beauty', NULL),                -- 14
('Automotive', NULL),            -- 15
('Garden', NULL),                -- 16
('Music', NULL),                 -- 17
('Office Supplies', NULL),       -- 18
('Pet Supplies', NULL),          -- 19
('Gaming', NULL);                -- 20


INSERT INTO Product (Name, Description, Price, QuantityOnHand, IsActive)
VALUES
('Smartphone A', 'High-end smartphone', 500.00, 50, TRUE),
('Laptop B', 'Gaming laptop', 1200.00, 30, TRUE),
('Camera C', 'Digital camera', 300.00, 20, TRUE),
('Microwave D', 'Convection microwave', 150.00, 15, TRUE),
('Sofa E', 'Comfortable sofa', 800.00, 5, TRUE),
('Novel F', 'Bestselling novel', 20.00, 100, TRUE),
('T-shirt G', 'Cotton t-shirt', 15.00, 200, TRUE),
('Football H', 'Official size football', 25.00, 150, TRUE),
('Toy Car I', 'Remote control car', 35.00, 60, TRUE),
('Organic Milk J', 'Fresh organic milk', 3.50, 80, TRUE),
('Vitamin K', 'Health supplement', 25.00, 70, TRUE),
('Lipstick L', 'Matte lipstick', 18.00, 90, TRUE),
('Car Tire M', 'Durable car tire', 100.00, 40, TRUE),
('Garden Hose N', '50ft garden hose', 35.00, 60, TRUE),
('Guitar O', 'Acoustic guitar', 250.00, 25, TRUE),
('Office Chair P', 'Ergonomic office chair', 150.00, 30, TRUE),
('Dog Food Q', 'Nutritious dog food', 40.00, 120, TRUE),
('Video Game R', 'Latest video game', 60.00, 90, TRUE),
('Printer S', 'Wireless printer', 200.00, 18, TRUE),
('Smartwatch T', 'Feature-rich smartwatch', 180.00, 55, TRUE);



-- For Product 1: Smartphone A in Mobiles (2) and Electronics (1)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (1, 2), (1, 1);
-- Product 2: Laptop B in Laptops (3) and Electronics (1)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (2, 3), (2, 1);
-- Product 3: Camera C in Cameras (4) and Electronics (1)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (3, 4), (3, 1);
-- Product 4: Microwave D in Home Appliances (5) and Kitchen (6)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (4, 5), (4, 6);
-- Product 5: Sofa E in Furniture (7)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (5, 7);
-- Product 6: Novel F in Books (8)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (6, 8);
-- Product 7: T-shirt G in Clothing (9)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (7, 9);
-- Product 8: Football H in Sports (10)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (8, 10);
-- Product 9: Toy Car I in Toys (11)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (9, 11);
-- Product 10: Organic Milk J in Groceries (12)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (10, 12);
-- Product 11: Vitamin K in Health (13)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (11, 13);
-- Product 12: Lipstick L in Beauty (14)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (12, 14);
-- Product 13: Car Tire M in Automotive (15)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (13, 15);
-- Product 14: Garden Hose N in Garden (16)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (14, 16);
-- Product 15: Guitar O in Music (17)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (15, 17);
-- Product 16: Office Chair P in Office Supplies (18)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (16, 18);
-- Product 17: Dog Food Q in Pet Supplies (19)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (17, 19);
-- Product 18: Video Game R in Gaming (20)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (18, 20);
-- Product 19: Printer S in Electronics (1) and Office Supplies (18)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (19, 1), (19, 18);
-- Product 20: Smartwatch T in Mobiles (2) and Electronics (1)
INSERT INTO ProductCategory (ProductID, CategoryID) VALUES (20, 2), (20, 1);


INSERT INTO Image (URL, ProductID) VALUES
('http://example.com/images/product1.jpg', 1),
('http://example.com/images/product2.jpg', 2),
('http://example.com/images/product3.jpg', 3),
('http://example.com/images/product4.jpg', 4),
('http://example.com/images/product5.jpg', 5),
('http://example.com/images/product6.jpg', 6),
('http://example.com/images/product7.jpg', 7),
('http://example.com/images/product8.jpg', 8),
('http://example.com/images/product9.jpg', 9),
('http://example.com/images/product10.jpg', 10),
('http://example.com/images/product11.jpg', 11),
('http://example.com/images/product12.jpg', 12),
('http://example.com/images/product13.jpg', 13),
('http://example.com/images/product14.jpg', 14),
('http://example.com/images/product15.jpg', 15),
('http://example.com/images/product16.jpg', 16),
('http://example.com/images/product17.jpg', 17),
('http://example.com/images/product18.jpg', 18),
('http://example.com/images/product19.jpg', 19),
('http://example.com/images/product20.jpg', 20);



INSERT INTO User (Username, Email, Password, Role) VALUES
('shopper1', 'shopper1@example.com', 'pass1', 'Shopper'),
('shopper2', 'shopper2@example.com', 'pass2', 'Shopper'),
('shopper3', 'shopper3@example.com', 'pass3', 'Shopper'),
('shopper4', 'shopper4@example.com', 'pass4', 'Shopper'),
('shopper5', 'shopper5@example.com', 'pass5', 'Shopper'),
('shopper6', 'shopper6@example.com', 'pass6', 'Shopper'),
('shopper7', 'shopper7@example.com', 'pass7', 'Shopper'),
('shopper8', 'shopper8@example.com', 'pass8', 'Shopper'),
('shopper9', 'shopper9@example.com', 'pass9', 'Shopper'),
('shopper10', 'shopper10@example.com', 'pass10', 'Shopper'),
('shopper11', 'shopper11@example.com', 'pass11', 'Shopper'),
('shopper12', 'shopper12@example.com', 'pass12', 'Shopper'),
('shopper13', 'shopper13@example.com', 'pass13', 'Shopper'),
('shopper14', 'shopper14@example.com', 'pass14', 'Shopper'),
('shopper15', 'shopper15@example.com', 'pass15', 'Shopper'),
('admin1', 'admin1@example.com', 'adminpass1', 'Administrator'),
('admin2', 'admin2@example.com', 'adminpass2', 'Administrator'),
('admin3', 'admin3@example.com', 'adminpass3', 'Administrator'),
('admin4', 'admin4@example.com', 'adminpass4', 'Administrator'),
('admin5', 'admin5@example.com', 'adminpass5', 'Administrator');



INSERT INTO ShippingAddress (ShopperID, Street, City, State, ZipCode, Country) VALUES
(1, '123 Main St', 'CityA', 'StateA', '10001', 'CountryX'),
(2, '124 Main St', 'CityB', 'StateA', '10002', 'CountryX'),
(3, '125 Main St', 'CityC', 'StateB', '10003', 'CountryX'),
(4, '126 Main St', 'CityD', 'StateB', '10004', 'CountryX'),
(5, '127 Main St', 'CityE', 'StateC', '10005', 'CountryX'),
(6, '128 Main St', 'CityF', 'StateC', '10006', 'CountryX'),
(7, '129 Main St', 'CityG', 'StateD', '10007', 'CountryX'),
(8, '130 Main St', 'CityH', 'StateD', '10008', 'CountryX'),
(9, '131 Main St', 'CityI', 'StateE', '10009', 'CountryX'),
(10, '132 Main St', 'CityJ', 'StateE', '10010', 'CountryX'),
(11, '133 Main St', 'CityK', 'StateF', '10011', 'CountryX'),
(12, '134 Main St', 'CityL', 'StateF', '10012', 'CountryX'),
(13, '135 Main St', 'CityM', 'StateG', '10013', 'CountryX'),
(14, '136 Main St', 'CityN', 'StateG', '10014', 'CountryX'),
(15, '137 Main St', 'CityO', 'StateH', '10015', 'CountryX'),
(16, '138 Main St', 'CityP', 'StateH', '10016', 'CountryX'),
(17, '139 Main St', 'CityQ', 'StateI', '10017', 'CountryX'),
(18, '140 Main St', 'CityR', 'StateI', '10018', 'CountryX'),
(19, '141 Main St', 'CityS', 'StateJ', '10019', 'CountryX'),
(20, '142 Main St', 'CityT', 'StateJ', '10020', 'CountryX');



INSERT INTO OrderTable (OrderDate, ShopperID) VALUES
(DATE_SUB(NOW(), INTERVAL 1 DAY), 1),
(DATE_SUB(NOW(), INTERVAL 2 DAY), 2),
(DATE_SUB(NOW(), INTERVAL 3 DAY), 3),
(DATE_SUB(NOW(), INTERVAL 4 DAY), 4),
(DATE_SUB(NOW(), INTERVAL 5 DAY), 5),
(DATE_SUB(NOW(), INTERVAL 6 DAY), 6),
(DATE_SUB(NOW(), INTERVAL 7 DAY), 7),
(DATE_SUB(NOW(), INTERVAL 8 DAY), 8),
(DATE_SUB(NOW(), INTERVAL 9 DAY), 9),
(DATE_SUB(NOW(), INTERVAL 10 DAY), 10),
(DATE_SUB(NOW(), INTERVAL 11 DAY), 11),
(DATE_SUB(NOW(), INTERVAL 12 DAY), 12),
(DATE_SUB(NOW(), INTERVAL 13 DAY), 13),
(DATE_SUB(NOW(), INTERVAL 14 DAY), 14),
(DATE_SUB(NOW(), INTERVAL 15 DAY), 15),
(DATE_SUB(NOW(), INTERVAL 16 DAY), 1),
(DATE_SUB(NOW(), INTERVAL 17 DAY), 2),
(DATE_SUB(NOW(), INTERVAL 18 DAY), 3),
(DATE_SUB(NOW(), INTERVAL 19 DAY), 4),
(DATE_SUB(NOW(), INTERVAL 20 DAY), 5);



INSERT INTO OrderItem (OrderID, ProductID, Quantity, Status) VALUES
(1, 1, 1, 'Shipped'),
(2, 2, 2, 'Shipped'),
(3, 3, 1, 'Canceled'),
(4, 4, 3, 'Shipped'),
(5, 5, 1, 'Returned'),
(6, 6, 2, 'Shipped'),
(7, 7, 1, 'Shipped'),
(8, 8, 2, 'Canceled'),
(9, 9, 1, 'Shipped'),
(10, 10, 4, 'Shipped'),
(11, 11, 1, 'Returned'),
(12, 12, 2, 'Shipped'),
(13, 13, 1, 'Shipped'),
(14, 14, 2, 'Canceled'),
(15, 15, 1, 'Shipped'),
(16, 16, 3, 'Shipped'),
(17, 17, 1, 'Returned'),
(18, 18, 2, 'Shipped'),
(19, 19, 1, 'Shipped'),
(20, 20, 2, 'Shipped');


-- End of sample data insertion.

