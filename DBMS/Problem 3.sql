
-- 3.1: Display Shopper’s information along with the number of orders 
--      placed during the last 30 days.
SELECT 
    u.UserID, 
    u.Username, 
    u.Email,
    COUNT(o.OrderID) AS OrderCount
FROM User u
LEFT JOIN OrderTable o 
    ON u.UserID = o.ShopperID 
    AND o.OrderDate >= (NOW() - INTERVAL 30 DAY)
WHERE u.Role = 'Shopper'
GROUP BY u.UserID, u.Username, u.Email;

-- 3.2: Display the top 10 Shoppers who generated maximum revenue in the last 30 days.
SELECT 
    u.UserID, 
    u.Username, 
    u.Email,
    SUM(oi.Quantity * p.Price) AS Revenue
FROM User u
JOIN OrderTable o ON u.UserID = o.ShopperID
JOIN OrderItem oi ON o.OrderID = oi.OrderID
JOIN Product p ON oi.ProductID = p.ProductID
WHERE u.Role = 'Shopper'
  AND o.OrderDate >= (NOW() - INTERVAL 30 DAY)
GROUP BY u.UserID, u.Username, u.Email
ORDER BY Revenue DESC
LIMIT 10;

-- 3.3: Display the top 20 Products which are ordered most in the last 60 days (by total quantity).
SELECT 
    p.ProductID, 
    p.Name, 
    SUM(oi.Quantity) AS TotalOrdered
FROM Product p
JOIN OrderItem oi ON p.ProductID = oi.ProductID
JOIN OrderTable o ON oi.OrderID = o.OrderID
WHERE o.OrderDate >= (NOW() - INTERVAL 60 DAY)
GROUP BY p.ProductID, p.Name
ORDER BY TotalOrdered DESC
LIMIT 20;

-- 3.4: Display the monthly sales revenue of the StoreFront for the last 6 months.
--      (Assuming revenue is the sum of OrderTotals calculated as SUM(Quantity * Price) per order)
SELECT 
    DATE_FORMAT(o.OrderDate, '%Y-%m') AS OrderMonth, 
    SUM(oi.Quantity * p.Price) AS MonthlyRevenue
FROM OrderTable o
JOIN OrderItem oi ON o.OrderID = oi.OrderID
JOIN Product p ON oi.ProductID = p.ProductID
WHERE o.OrderDate >= (NOW() - INTERVAL 6 MONTH)
GROUP BY OrderMonth
ORDER BY OrderMonth;

-- 3.5: Mark products as Inactive which have not been ordered in the last 90 days.
UPDATE Product
SET IsActive = FALSE
WHERE ProductID NOT IN (
    SELECT DISTINCT oi.ProductID
    FROM OrderItem oi
    JOIN OrderTable o ON oi.OrderID = o.OrderID
    WHERE o.OrderDate >= (NOW() - INTERVAL 90 DAY)
);

-- 3.6: Given a category search keyword, display all the products present in that/those category/categories.
--      For demonstration, using the keyword 'Mobile'.
SELECT 
    p.ProductID, 
    p.Name, 
    p.Description, 
    p.Price
FROM Product p
JOIN ProductCategory pc ON p.ProductID = pc.ProductID
JOIN Category c ON pc.CategoryID = c.CategoryID
WHERE c.Name LIKE '%Mobile%';

-- 3.7: Display the top 10 items which were canceled the most.
SELECT 
    p.ProductID, 
    p.Name, 
    COUNT(oi.OrderItemID) AS CanceledCount
FROM Product p
JOIN OrderItem oi ON p.ProductID = oi.ProductID
WHERE oi.Status = 'Canceled'
GROUP BY p.ProductID, p.Name
ORDER BY CanceledCount DESC
LIMIT 10;
