
DELIMITER $$

-- 2.1: Stored procedure to retrieve average sales of each product in a month.
-- Sales are computed as revenue: Price * Quantity.
-- The procedure returns: ProductID, Product Name, and AverageSales.
CREATE PROCEDURE sp_GetAverageSalesByProduct(IN p_month INT, IN p_year INT)
BEGIN
    SELECT 
        P.ProductID,
        P.Name,
        AVG(P.Price * OI.Quantity) AS AverageSales
    FROM OrderTable O
    JOIN OrderItem OI ON O.OrderID = OI.OrderID
    JOIN Product P ON OI.ProductID = P.ProductID
    WHERE MONTH(O.OrderDate) = p_month
      AND YEAR(O.OrderDate) = p_year
    GROUP BY P.ProductID, P.Name;
END $$

-- 2.2: Stored procedure to retrieve order details with status for a given period.
-- Input parameters: p_start_date and p_end_date.
-- If p_start_date > p_end_date, the procedure resets the start date to the first day of that month.
CREATE PROCEDURE sp_GetOrderDetailsByPeriod(IN p_start_date DATE, IN p_end_date DATE)
BEGIN
    DECLARE v_start_date DATE;
    
    -- Validate the input dates.
    IF p_start_date > p_end_date THEN
        SET v_start_date = DATE_FORMAT(p_start_date, '%Y-%m-01');
    ELSE
        SET v_start_date = p_start_date;
    END IF;
    
    SELECT 
        O.OrderID,
        O.OrderDate,
        O.ShopperID,
        OI.OrderItemID,
        OI.ProductID,
        OI.Quantity,
        OI.Status
    FROM OrderTable O
    JOIN OrderItem OI ON O.OrderID = OI.OrderID
    WHERE O.OrderDate BETWEEN v_start_date AND p_end_date
    ORDER BY O.OrderDate;
END $$

DELIMITER ;
