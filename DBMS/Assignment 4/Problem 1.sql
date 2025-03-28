
DELIMITER $$

-- 1.1: Function to calculate number of orders in a month.
CREATE FUNCTION GetMonthlyOrderCount(p_month INT, p_year INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE order_count INT;
    SELECT COUNT(*) INTO order_count
    FROM OrderTable
    WHERE MONTH(OrderDate) = p_month
      AND YEAR(OrderDate) = p_year;
    RETURN order_count;
END $$

-- 1.2: Function to return the month in a year having maximum orders.
-- Returns the month (1-12) with the highest order count for the given year.
CREATE FUNCTION GetMonthWithMaxOrders(p_year INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE max_month INT DEFAULT 0;
    SELECT MONTH(OrderDate) INTO max_month
    FROM OrderTable
    WHERE YEAR(OrderDate) = p_year
    GROUP BY MONTH(OrderDate)
    ORDER BY COUNT(*) DESC
    LIMIT 1;
    RETURN max_month;
END $$

DELIMITER ;
