# Write your MySQL query statement below
SELECT 
    product_id, 
    new_price AS price
FROM 
    Products
WHERE 
    (product_id, change_date) IN (
        -- The Hardware L1 Cache: Find the exact maximum valid timestamp for these products
        SELECT 
            product_id, 
            MAX(change_date)
        FROM 
            Products
        WHERE 
            change_date <= '2019-08-16'
        GROUP BY 
            product_id
    )

UNION ALL

-- STREAM 2: The Default Fallback State
-- Products whose ENTIRE timeline exists strictly after the target date.
SELECT DISTINCT 
    product_id, 
    10 AS price
FROM 
    Products
WHERE 
    product_id NOT IN (
        -- We reuse the exact same filtering condition.
        -- The database execution engine will cache this subquery!
        SELECT 
            product_id
        FROM 
            Products
        WHERE 
            change_date <= '2019-08-16'
    );