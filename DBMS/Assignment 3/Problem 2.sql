
-- 1. Display list of products (Id, Title, Count of Categories) 
--    which fall in more than one category.
SELECT 
    p.ProductID, 
    p.Name, 
    COUNT(pc.CategoryId) AS CategoryCount
FROM Product p
JOIN ProductCategory pc ON p.ProductID = pc.ProductId
GROUP BY p.ProductID, p.ProductID
HAVING COUNT(pc.CategoryId) > 1;

-- 2. Display count of products as per price range.

SELECT '0 - 100' AS PriceRange, COUNT(*) AS ProductCount
FROM Product 
WHERE Price BETWEEN 0 AND 100
UNION ALL
SELECT '101 - 500', COUNT(*)
FROM Product 
WHERE Price BETWEEN 101 AND 500
UNION ALL
SELECT 'Above 500', COUNT(*)
FROM Product 
WHERE Price > 500;



-- 2.3: Display the categories along with the number of products under each category.
SELECT 
    c.CategoryID, 
    c.Name, 
    COUNT(pc.ProductID) AS ProductCount
FROM Category c
LEFT JOIN ProductCategory pc ON c.CategoryID = pc.CategoryID
GROUP BY c.CategoryID, c.Name;


