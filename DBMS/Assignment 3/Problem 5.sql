
/********************************************************************
 * ASSIGNMENT 5: Creating and Using a View for Order Information
 ********************************************************************/

/*
 * 5.1: Create a view displaying the order information for the last 60 days.
 *      The view includes:
 *         - OrderID, Product Name, Price,
 *         - Shopper’s (User) Username and Email,
 *         - OrderDate,
 *         - OrderItem Status (as OrderStatus)
 *      Results are ordered by OrderDate descending.
 */
CREATE OR REPLACE VIEW OrderInfo AS
SELECT 
    o.OrderID,
    p.Name AS ProductName,
    p.Price,
    u.Username AS ShopperName,
    u.Email,
    o.OrderDate,
    oi.Status AS OrderStatus
FROM OrderTable o
JOIN OrderItem oi ON o.OrderID = oi.OrderID
JOIN Product p ON oi.ProductID = p.ProductID
JOIN User u ON o.ShopperID = u.UserID
WHERE o.OrderDate >= (NOW() - INTERVAL 60 DAY)
ORDER BY o.OrderDate DESC;

-- 5.2: Use the view to display the products (items) which are in 'Shipped' state.
SELECT *
FROM OrderInfo
WHERE OrderStatus = 'Shipped';

-- 5.3: Use the view to display the top 5 most selling products.
--      (Here we group by ProductName; each row in the view represents one sold unit.)
SELECT 
    ProductName, 
    COUNT(*) AS SoldCount
FROM OrderInfo
GROUP BY ProductName
ORDER BY SoldCount DESC
LIMIT 5;
