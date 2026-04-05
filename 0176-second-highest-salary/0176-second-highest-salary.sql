# Write your MySQL query statement below
WITH HeavyRankings AS (
    SELECT 
        salary,
        DENSE_RANK() OVER (ORDER BY salary DESC) AS heavy_rank
    FROM Employee
),
FilterSecond AS (
    SELECT salary 
    FROM HeavyRankings 
    WHERE heavy_rank = 2
)
-- The Clunky Trap: Wrapping the final output in an aggregate to force a NULL if the table is empty
SELECT (
    SELECT MAX(salary) 
    FROM FilterSecond
) AS SecondHighestSalary;
