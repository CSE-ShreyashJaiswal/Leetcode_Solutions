class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Suboptimal Heavy Lifting: A massive nested List instead of O(1) in-place primitive updates
        List<List<Integer>> dpGrid = new ArrayList<>();
        
        // Build the heavy 2D memory bank and fill it with 0s
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0); // Auto-boxing overhead happening thousands of times!
            }
            dpGrid.add(row);
        }
        
        // Seed our starting position with the actual toll cost of the first cell
        dpGrid.get(0).set(0, grid[0][0]);
        
        // Pre-fill the top row: The only way to reach these cells is by moving strictly RIGHT
        for (int j = 1; j < n; j++) {
            int previousCost = dpGrid.get(0).get(j - 1);
            dpGrid.get(0).set(j, previousCost + grid[0][j]);
        }
        
        // Pre-fill the left column: The only way to reach these cells is by moving strictly DOWN
        for (int i = 1; i < m; i++) {
            int previousCost = dpGrid.get(i - 1).get(0);
            dpGrid.get(i).set(0, previousCost + grid[i][0]);
        }
        
        // Walk through the rest of the grid starting from (1, 1)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                
                // Look strictly UP and strictly LEFT in our heavy list
                int costFromTop = dpGrid.get(i - 1).get(j);
                int costFromLeft = dpGrid.get(i).get(j - 1);
                
                // The minimum cost to reach this cell is its own toll PLUS the cheaper of the two previous paths
                int cheapestPreviousPath = Math.min(costFromTop, costFromLeft);
                dpGrid.get(i).set(j, cheapestPreviousPath + grid[i][j]);
            }
        }
        
        // The final answer is waiting for us in the bottom-right corner!
        return dpGrid.get(m - 1).get(n - 1);
    }
}