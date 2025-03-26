
-- 2.1 Display Id, Title (Name), Category Title, Price of the products which are Active.
-- Active products are determined by IsActive = TRUE.
SELECT
    P.ProductID AS Id,
    P.Name AS Title,
    C.Name AS CategoryTitle,
    P.Price
FROM Product P
JOIN ProductCategory PC ON P.ProductID = PC.ProductID
JOIN Category C ON PC.CategoryID = C.CategoryID
WHERE P.IsActive = TRUE
ORDER BY P.ProductID DESC;


----------------------------------------------------------------


-- 2.2 Display the list of products which don't have any images.
SELECT 
    P.ProductID AS Id,
    P.Name AS Title,
    P.Price
FROM Product P
LEFT JOIN Image I ON P.ProductID = I.ProductID
WHERE I.ImageID IS NULL;


----------------------------------------------------------------


-- 2.3 Display all Id, Title and Parent Category Title for all the Categories listed,
-- sorted by Parent Category Title and then Category Title.
-- If a category is a top category then Parent Category Title should display 'Top Category'.
SELECT 
    C.CategoryID AS Id,
    C.Name AS Title,
    COALESCE(P.Name, 'Top Category') AS ParentCategoryTitle
FROM Category C
LEFT JOIN Category P ON C.ParentCategoryID = P.CategoryID
ORDER BY ParentCategoryTitle, Title;


----------------------------------------------------------------


-- 2.4 Display Id, Title and Parent Category Title of all the leaf Categories 
-- (categories which are not a parent of any other category).
SELECT 
    C.CategoryID AS Id,
    C.Name AS Title,
    COALESCE(P.Name, 'Top Category') AS ParentCategoryTitle
FROM Category C
LEFT JOIN Category CChild ON C.CategoryID = CChild.ParentCategoryID
LEFT JOIN Category P ON C.ParentCategoryID = P.CategoryID
WHERE CChild.CategoryID IS NULL
ORDER BY C.CategoryID;


----------------------------------------------------------------


-- 2.5 Display Product Title, Price & Description for products which fall into a particular category.
-- For example, for category Title "Mobile" (or using a LIKE clause to match "Mobile Phones").
SELECT 
    P.Name AS ProductTitle,
    P.Price,
    P.Description
FROM Product P
JOIN ProductCategory PC ON P.ProductID = PC.ProductID
JOIN Category C ON PC.CategoryID = C.CategoryID
WHERE C.Name  =  'Mobiles';


----------------------------------------------------------------


-- 2.6 Display the list of Products whose Quantity on hand (Inventory) is under 50.
SELECT 
    ProductID AS Id,
    Name AS Title,
    QuantityOnHand AS Inventory
FROM Product
WHERE QuantityOnHand < 50;

