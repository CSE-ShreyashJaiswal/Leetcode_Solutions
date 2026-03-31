class Solution {
    public int cherryPickup(int[][] grid) {
       int m = grid.length;
        int n = grid[0].length;
        
        // Suboptimal Heavy Lifting: A massive 3D Object array instead of a 2D primitive array!
        // This forces the JVM to deal with thousands of heavy Integer objects.
        Integer[][][] memoBank = new Integer[m][n][n];
        
        // Robot 1 starts at (0, 0) and Robot 2 starts at (0, n - 1)
        return calculate(grid, 0, 0, n - 1, memoBank); 
    }

    private int calculate(int[][] grid, int row, int col1, int col2, Integer[][][] memoBank) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Base case: If either robot falls off the sides of the grid, this is an invalid path
        if (col1 < 0 || col1 >= n || col2 < 0 || col2 >= n) {
            return 0; // Return 0 cherries so it doesn't affect our Math.max calculations
        }
        
        // Base case: We successfully reached beyond the bottom row
        if (row == m) {
            return 0;
        }
        
        // The Clunky Trap: Forcing an Auto-Boxing null check and unboxing operation!
        if (memoBank[row][col1][col2] != null) {
            return memoBank[row][col1][col2];
        }
        
        // Calculate the cherries picked up in the CURRENT row
        int currentCherries = 0;
        if (col1 == col2) {
            // If both robots land on the exact same cell, they only collect the cherries ONCE
            currentCherries = grid[row][col1];
        } else {
            // Otherwise, they both grab the cherries from their respective independent cells
            currentCherries = grid[row][col1] + grid[row][col2];
        }
        
        // Now, simulate both robots moving down to the next row.
        // Robot 1 can move 3 ways (-1, 0, 1) and Robot 2 can move 3 ways (-1, 0, 1).
        // That means there are exactly 3 * 3 = 9 possible combinations of future moves!
        int maxNextCherries = 0;
        for (int move1 = -1; move1 <= 1; move1++) {
            for (int move2 = -1; move2 <= 1; move2++) {
                
                // Recursively find the best path out of all 9 options
                int nextPath = calculate(grid, row + 1, col1 + move1, col2 + move2, memoBank);
                maxNextCherries = Math.max(maxNextCherries, nextPath);
            }
        }
        
        // The absolute best path from this state is our current loot + the best future loot
        int totalCherries = currentCherries + maxNextCherries;
        
        // Store the heavy Integer object in our 3D memory bank
        memoBank[row][col1][col2] = totalCherries;
        
        return totalCherries;
    }
}