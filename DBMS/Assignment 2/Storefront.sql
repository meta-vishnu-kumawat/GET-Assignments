CREATE DATABASE Storefront;
USE Storefront;
DROP DATABASE storefront;

CREATE TABLE Category (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,   
    Name VARCHAR(100) NOT NULL,                    
    ParentCategoryID INT,                          
    CONSTRAINT fk_parent_category FOREIGN KEY (ParentCategoryID)
        REFERENCES Category(CategoryID)
        ON DELETE SET NULL                         
);

-- Create the Product table
CREATE TABLE Product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,      
    Name VARCHAR(100) NOT NULL,                     
    Description TEXT,                                
    Price DECIMAL(10, 2) NOT NULL,                 
    StockQuantity INT NOT NULL                       
);

-- Create the ProductCategory 
CREATE TABLE ProductCategory (
    ProductID INT NOT NULL,                          
    CategoryID INT NOT NULL,                         
    PRIMARY KEY (ProductID, CategoryID),             
    CONSTRAINT fk_pc_product FOREIGN KEY (ProductID)
        REFERENCES Product(ProductID)
        ON DELETE CASCADE,                        
    CONSTRAINT fk_pc_category FOREIGN KEY (CategoryID)
        REFERENCES Category(CategoryID)
        ON DELETE CASCADE                           
);

--  Create the Image table for product images
CREATE TABLE Image (
    ImageID INT AUTO_INCREMENT PRIMARY KEY,       
    URL VARCHAR(255) NOT NULL,                       
    ProductID INT NOT NULL,                         
    CONSTRAINT fk_image_product FOREIGN KEY (ProductID)
        REFERENCES Product(ProductID)
        ON DELETE CASCADE                          
);

--  Create the User table
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,        
    Username VARCHAR(50) NOT NULL,                  
    Email VARCHAR(100) NOT NULL UNIQUE,              
    Password VARCHAR(255) NOT NULL,                  
    Role ENUM('Shopper', 'Administrator') NOT NULL   
);

-- Create the ShippingAddress table for shopper addresses
CREATE TABLE ShippingAddress (
    AddressID INT AUTO_INCREMENT PRIMARY KEY,        
    ShopperID INT NOT NULL,                         
    Street VARCHAR(255) NOT NULL,                   
    City VARCHAR(100) NOT NULL,                     
    State VARCHAR(100) NOT NULL,                  
    ZipCode VARCHAR(20) NOT NULL,                   
    Country VARCHAR(100) NOT NULL,                 
    CONSTRAINT fk_address_user FOREIGN KEY (ShopperID)
        REFERENCES User(UserID)
        ON DELETE CASCADE                          
);

--  Create the Order table for shopper orders
CREATE TABLE OrderTable (   
    OrderID INT AUTO_INCREMENT PRIMARY KEY,         
    OrderDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,  
    ShopperID INT NOT NULL,                          
    CONSTRAINT fk_order_user FOREIGN KEY (ShopperID)
        REFERENCES User(UserID)
        ON DELETE CASCADE                            
);

--  Create the OrderItem table for items in an order
CREATE TABLE OrderItem (
    OrderItemID INT AUTO_INCREMENT PRIMARY KEY,      
    OrderID INT NOT NULL,                            
    ProductID INT NOT NULL,                        
    Quantity INT NOT NULL,                          
    Status ENUM('Shipped', 'Canceled', 'Returned') NOT NULL,  
    CONSTRAINT fk_orderitem_order FOREIGN KEY (OrderID)
        REFERENCES OrderTable(OrderID)
        ON DELETE CASCADE,                           
    CONSTRAINT fk_orderitem_product FOREIGN KEY (ProductID)
        REFERENCES Product(ProductID)
        ON DELETE CASCADE                           
);
