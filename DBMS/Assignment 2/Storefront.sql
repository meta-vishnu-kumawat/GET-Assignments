CREATE DATABASE Storefront;
USE Storefront;
DROP DATABASE storefront;
-- Step 5.1: Create the Category table (with self-reference for nested categories)
CREATE TABLE Category (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,   -- Unique identifier for each category
    Name VARCHAR(100) NOT NULL,                    -- Name of the category
    ParentCategoryID INT,                          -- For nested categories (can be NULL)
    CONSTRAINT fk_parent_category FOREIGN KEY (ParentCategoryID)
        REFERENCES Category(CategoryID)
        ON DELETE SET NULL                         -- If parent category is deleted, set to NULL
);

-- Step 5.2: Create the Product table
CREATE TABLE Product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,      -- Unique identifier for each product
    Name VARCHAR(100) NOT NULL,                      -- Product name
    Description TEXT,                                -- Product description
    Price DECIMAL(10, 2) NOT NULL,                   -- Product price
    StockQuantity INT NOT NULL                       -- Stock quantity to check availability
);

-- Step 5.3: Create the ProductCategory junction table for many-to-many relationship
CREATE TABLE ProductCategory (
    ProductID INT NOT NULL,                          -- Foreign key to Product
    CategoryID INT NOT NULL,                         -- Foreign key to Category
    PRIMARY KEY (ProductID, CategoryID),             -- Composite primary key
    CONSTRAINT fk_pc_product FOREIGN KEY (ProductID)
        REFERENCES Product(ProductID)
        ON DELETE CASCADE,                          -- Cascade delete if product is removed
    CONSTRAINT fk_pc_category FOREIGN KEY (CategoryID)
        REFERENCES Category(CategoryID)
        ON DELETE CASCADE                           -- Cascade delete if category is removed
);

-- Step 5.4: Create the Image table for product images
CREATE TABLE Image (
    ImageID INT AUTO_INCREMENT PRIMARY KEY,        -- Unique identifier for each image
    URL VARCHAR(255) NOT NULL,                       -- URL or file path of the image
    ProductID INT NOT NULL,                          -- Foreign key to Product
    CONSTRAINT fk_image_product FOREIGN KEY (ProductID)
        REFERENCES Product(ProductID)
        ON DELETE CASCADE                           -- Delete images if product is removed
);

-- Step 5.5: Create the User table (for both shoppers and administrators)
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,         -- Unique identifier for each user
    Username VARCHAR(50) NOT NULL,                   -- Username for login
    Email VARCHAR(100) NOT NULL UNIQUE,              -- Email address (unique)
    Password VARCHAR(255) NOT NULL,                  -- Password (store hashed)
    Role ENUM('Shopper', 'Administrator') NOT NULL   -- Role to distinguish user type
);

-- Step 5.6: Create the ShippingAddress table for shopper addresses
CREATE TABLE ShippingAddress (
    AddressID INT AUTO_INCREMENT PRIMARY KEY,        -- Unique identifier for each address
    ShopperID INT NOT NULL,                          -- Foreign key to User (only shoppers)
    Street VARCHAR(255) NOT NULL,                    -- Street address
    City VARCHAR(100) NOT NULL,                      -- City
    State VARCHAR(100) NOT NULL,                     -- State
    ZipCode VARCHAR(20) NOT NULL,                    -- Zip/Postal code
    Country VARCHAR(100) NOT NULL,                   -- Country
    CONSTRAINT fk_address_user FOREIGN KEY (ShopperID)
        REFERENCES User(UserID)
        ON DELETE CASCADE                           -- Delete addresses if user is removed
);

-- Step 5.7: Create the Order table for shopper orders
CREATE TABLE OrderTable (   -- Using OrderTable since "Order" is a reserved word in SQL
    OrderID INT AUTO_INCREMENT PRIMARY KEY,         -- Unique identifier for each order
    OrderDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- Date and time of the order
    ShopperID INT NOT NULL,                           -- Foreign key to User (shopper placing the order)
    CONSTRAINT fk_order_user FOREIGN KEY (ShopperID)
        REFERENCES User(UserID)
        ON DELETE CASCADE                            -- Delete orders if shopper is removed
);

-- Step 5.8: Create the OrderItem table for items in an order
CREATE TABLE OrderItem (
    OrderItemID INT AUTO_INCREMENT PRIMARY KEY,      -- Unique identifier for each order item
    OrderID INT NOT NULL,                            -- Foreign key to OrderTable
    ProductID INT NOT NULL,                          -- Foreign key to Product
    Quantity INT NOT NULL,                           -- Quantity of the product ordered
    Status ENUM('Shipped', 'Canceled', 'Returned') NOT NULL,  -- Status of this order item
    CONSTRAINT fk_orderitem_order FOREIGN KEY (OrderID)
        REFERENCES OrderTable(OrderID)
        ON DELETE CASCADE,                           -- Delete order items if order is removed
    CONSTRAINT fk_orderitem_product FOREIGN KEY (ProductID)
        REFERENCES Product(ProductID)
        ON DELETE CASCADE                            -- Delete order items if product is removed
);
