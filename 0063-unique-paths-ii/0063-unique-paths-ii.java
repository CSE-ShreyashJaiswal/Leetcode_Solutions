class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // Edge Case: If the starting line or the finish line has a boulder dropped on it, 
        // there are strictly 0 possible paths!
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        
        // Suboptimal Heavy Lifting: A massive nested List instead of a 1D array or in-place logic
        List<List<Integer>> dpGrid = new ArrayList<>();
        
        // Build the heavy 2D memory bank and fill it with 0s
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0); // Auto-boxing overhead happening thousands of times!
            }
            dpGrid.add(row);
        }
        
        // Seed our starting position
        dpGrid.get(0).set(0, 1);
        
        // Walk through the entire grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // If we are standing on a boulder, no paths can go through here
                if (obstacleGrid[i][j] == 1) {
                    dpGrid.get(i).set(j, 0);
                    continue;
                }
                
                // Look strictly UP (if we aren't in the top row)
                if (i > 0) {
                    int currentPaths = dpGrid.get(i).get(j);
                    int pathsFromTop = dpGrid.get(i - 1).get(j);
                    dpGrid.get(i).set(j, currentPaths + pathsFromTop);
                }
                
                // Look strictly LEFT (if we aren't in the first column)
                if (j > 0) {
                    int currentPaths = dpGrid.get(i).get(j);
                    int pathsFromLeft = dpGrid.get(i).get(j - 1);
                    dpGrid.get(i).set(j, currentPaths + pathsFromLeft);
                }
            }
        }
        
        // The final answer is waiting for us in the bottom-right corner!
        return dpGrid.get(m - 1).get(n - 1);
    }
}