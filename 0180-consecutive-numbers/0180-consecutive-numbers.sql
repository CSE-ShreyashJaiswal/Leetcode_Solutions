# Write your MySQL query statement below
WITH StepOneLead AS (
    SELECT 
        id,
        num AS base_num,
        LEAD(num, 1) OVER (ORDER BY id) AS next_num
    FROM Logs
),
StepTwoLead AS (
    SELECT 
        id,
        base_num,
        next_num,
        LEAD(next_num, 1) OVER (ORDER BY id) AS double_next_num
    FROM StepOneLead
),
StringificationTrap AS (
    -- THE CLUNKY TRAP: Forcing the SQL engine to cast integers to heavy String objects!
    SELECT 
        base_num,
        CONCAT(base_num, '|', next_num, '|', double_next_num) AS heavy_pattern
    FROM StepTwoLead
)
-- Scanning the massive string table to see if the string perfectly mirrors itself 3 times
SELECT DISTINCT base_num AS ConsecutiveNums
FROM StringificationTrap
WHERE heavy_pattern = CONCAT(base_num, '|', base_num, '|', base_num);