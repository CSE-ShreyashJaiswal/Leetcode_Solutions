CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      -- Suboptimal Heavy Lifting: An O(N^2) cross-table scan inside a User-Defined Function!
      WITH HeavyCrossComparison AS (
          SELECT 
              e1.salary AS target_salary,
              -- Forcing the database to scan the whole table AGAIN for every single employee
              (SELECT COUNT(DISTINCT e2.salary) FROM Employee e2 WHERE e2.salary > e1.salary) AS higher_count
          FROM Employee e1
      ),
      ExactMatch AS (
          SELECT target_salary 
          FROM HeavyCrossComparison 
          WHERE higher_count = N - 1
      )
      -- Compress any duplicates and safely return NULL if the rank doesn't exist
      SELECT MAX(target_salary) 
      FROM ExactMatch
  );
END