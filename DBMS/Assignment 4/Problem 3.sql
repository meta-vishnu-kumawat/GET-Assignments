-- For OrderTable: Index on ShopperID (for joins) and OrderDate (for filtering by date)
CREATE INDEX idx_order_shopperid ON OrderTable(ShopperID);
CREATE INDEX idx_order_orderdate ON OrderTable(OrderDate);

-- For Product table: Index on IsActive (used in filtering) and Price (for range queries)
CREATE INDEX idx_product_isactive ON Product(IsActive);
CREATE INDEX idx_product_price ON Product(Price);

-- For Category table: Index on ParentCategoryID (for self-joins) and Name (for search queries)
CREATE INDEX idx_category_parent ON Category(ParentCategoryID);
CREATE INDEX idx_category_name ON Category(Name);
