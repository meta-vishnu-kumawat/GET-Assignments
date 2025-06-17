
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

 CREATE VIEW OrderDeatils AS
 SELECT OrderID,ProductName,Price,ShopperName,Email,OrderDate,OrderStatus
 FROM OrderInfo WHERE OrderDate >= (NOW() - INTERVAL 60 DAY)
 ORDER BY OrderDate DESC;
 SELECT * FROM OrderDeatils;

-- 5.2: Use the view to display the products (items) which are in 'Shipped' state.
SELECT *
FROM OrderDeatils
WHERE OrderStatus = 'Shipped';

-- 5.3: Use the view to display the top 5 most selling products.
--      (Here we group by ProductName; each row in the view represents one sold unit.)
SELECT 
    ProductName, 
    COUNT(*) AS SoldCount
FROM OrderDeatils
GROUP BY ProductName
ORDER BY SoldCount DESC
LIMIT 5;


