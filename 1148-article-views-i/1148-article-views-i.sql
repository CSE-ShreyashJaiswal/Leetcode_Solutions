# Write your MySQL query statement below
WITH HeavyViewEvaluation AS (
    SELECT 
        article_id,
        author_id,
        viewer_id,
        -- THE CLUNKY TRAP: Forcing the engine to evaluate every single row 
        -- instead of just filtering out the bad ones immediately!
        CASE 
            WHEN author_id = viewer_id THEN 1 
            ELSE 0 
        END AS is_own_view
    FROM Views
),
MassiveAggregation AS (
    SELECT 
        author_id,
        -- Forcing the database to physically calculate the total sum of self-views
        SUM(is_own_view) AS total_self_views
    FROM HeavyViewEvaluation
    -- Grouping requires a massive sorting and hashing operation under the hood!
    GROUP BY author_id
)
-- Finally, scanning the second temporary table to filter based on our heavy math
SELECT 
    author_id AS id
FROM MassiveAggregation
WHERE total_self_views > 0
ORDER BY id ASC;