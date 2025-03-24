-- 3.1 Display Recent 50 Orders placed (Id, Order Date, Order Total).
SELECT O.OrderID AS Id,O.OrderDate,SUM(P.Price * OI.Quantity) AS OrderTotal
FROM OrderTable O
JOIN OrderItem OI ON O.OrderID = OI.OrderID
JOIN Product P ON OI.ProductID = P.ProductID
GROUP BY O.OrderID, O.OrderDate
ORDER BY O.OrderDate DESC
LIMIT 50;

-- 3.2 Display 10 most expensive Orders.
SELECT O.OrderID AS Id,O.OrderDate,SUM(P.Price * OI.Quantity) AS OrderTotal
FROM OrderTable O
JOIN OrderItem OI ON O.OrderID = OI.OrderID
JOIN Product P ON OI.ProductID = P.ProductID
GROUP BY O.OrderID, O.OrderDate
ORDER BY OrderTotal DESC
LIMIT 10;

-- 3.3 Display all Orders placed more than 10 days ago and with one or more items not shipped.
SELECT DISTINCT
    O.OrderID,
    O.OrderDate
FROM OrderTable O
JOIN OrderItem OI ON O.OrderID = OI.OrderID
WHERE O.OrderDate < DATE_SUB(CURRENT_DATE, INTERVAL 10 DAY)
  AND OI.Status <> 'Shipped';

-- 3.4 Display list of shoppers which haven't ordered anything since last month.
SELECT U.UserID,U.Username,U.Email
FROM User U
WHERE U.Role = 'Shopper'
  AND U.UserID NOT IN (
    SELECT DISTINCT ShopperID 
    FROM OrderTable 
    WHERE OrderDate >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH), '%Y-%m-01')
  );

-- 3.5 Display list of shoppers along with orders placed by them in last 15 days.
SELECT 
    U.UserID,
    U.Username,
    O.OrderID,
    O.OrderDate
FROM User U
JOIN OrderTable O ON U.UserID = O.ShopperID
WHERE U.Role = 'Shopper'
  AND O.OrderDate >= DATE_SUB(CURRENT_DATE, INTERVAL 15 DAY)
ORDER BY O.OrderDate DESC;

-- 3.6 Display list of order items which are in 'Shipped' state for a particular Order Id (e.g., 1020).
-- (Replace 1020 with the desired OrderID)
SELECT OrderItemID,OrderID,ProductID,Quantity,Status
FROM OrderItem
WHERE OrderID = 1020
  AND Status = 'Shipped';

-- 3.7 Display list of order items along with order placed date which fall between Rs 20 to Rs 50 price.

SELECT OI.OrderItemID,O.OrderDate,P.Name AS ProductTitle,P.Price,OI.Quantity,(P.Price * OI.Quantity) AS ItemTotal
FROM OrderItem OI
JOIN OrderTable O ON OI.OrderID = O.OrderID
JOIN Product P ON OI.ProductID = P.ProductID
WHERE P.Price BETWEEN 20 AND 50
ORDER BY O.OrderDate;
