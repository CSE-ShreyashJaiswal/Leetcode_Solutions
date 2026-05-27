# Write your MySQL query statement below
SELECT 
    s.user_id, 
    -- THE BOOLEAN AVERAGE:
    -- If action is 'confirmed', it evaluates to 1. Otherwise, 0.
    -- AVG() automatically sums these 1s and divides by the total row count!
    -- IFNULL catches the edge case where a user has strictly 0 records (AVG returns NULL).
    ROUND(IFNULL(AVG(c.action = 'confirmed'), 0), 2) AS confirmation_rate
FROM 
    Signups s
LEFT JOIN 
    Confirmations c 
    ON s.user_id = c.user_id
GROUP BY 
    s.user_id;