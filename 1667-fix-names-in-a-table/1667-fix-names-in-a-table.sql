# Write your MySQL query statement below
WITH HeavyStringExtraction AS (
    SELECT 
        user_id,
        name AS original_name,
        -- Physically severing the string into separate memory columns
        LEFT(name, 1) AS first_letter,
        RIGHT(name, CHAR_LENGTH(name) - 1) AS rest_of_name,
        CHAR_LENGTH(name) AS name_length
    FROM Users
),
HeavyCaseFormatting AS (
    SELECT 
        user_id,
        -- Applying the transformations in a completely separate pipeline step
        UPPER(first_letter) AS capitalized_first,
        
        -- The Clunky Trap: Using a heavy CASE WHEN evaluation instead of a native SUBSTRING safe-fail
        CASE 
            WHEN name_length > 1 THEN LOWER(rest_of_name)
            ELSE ''
        END AS lowercased_rest
    FROM HeavyStringExtraction
)
-- Finally, scanning the second temporary table to stitch the disposable strings back together
SELECT 
    user_id,
    CONCAT(capitalized_first, lowercased_rest) AS name
FROM HeavyCaseFormatting
ORDER BY user_id ASC;