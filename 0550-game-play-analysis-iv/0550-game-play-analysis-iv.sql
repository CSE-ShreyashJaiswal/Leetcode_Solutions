-- Step 1: The Isolated Memory Pipeline
WITH FirstLogins AS (
    -- Mathematically compress the raw data down to just the absolute first physical footprint
    SELECT 
        player_id, 
        MIN(event_date) AS first_login
    FROM 
        Activity
    GROUP BY 
        player_id
)

-- Step 2: The Precision Probe and Calculation
SELECT 
    -- If the LEFT JOIN found a match, A.player_id is populated. If not, it is NULL.
    -- COUNT() automatically ignores NULLs, giving us the exact numerator!
    ROUND(COUNT(A.player_id) / COUNT(F.player_id), 2) AS fraction
FROM 
    FirstLogins F
LEFT JOIN 
    Activity A 
    ON F.player_id = A.player_id 
    -- The hardware-level date mathematical shift
    AND A.event_date = DATE_ADD(F.first_login, INTERVAL 1 DAY);