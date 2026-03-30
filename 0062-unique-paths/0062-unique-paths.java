class Solution {
    public int uniquePaths(int m, int n) {
        List<List<Integer>> dpGrid = new ArrayList<>();
        
        // Build the heavy 2D memory bank
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                
                // Base cases: The top row and left column only have exactly 1 possible path
                // to reach them (going straight right, or straight down)
                if (i == 0 || j == 0) {
                    row.add(1); // Auto-boxing overhead: primitive 1 into an Integer object
                } else {
                    row.add(0); // Placeholder for the rest of the grid
                }
            }
            dpGrid.add(row);
        }
        
        // Walk through the rest of the grid starting from (1, 1)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                
                // Look strictly UP and strictly LEFT in our heavy list
                int pathsFromTop = dpGrid.get(i - 1).get(j);
                int pathsFromLeft = dpGrid.get(i).get(j - 1);
                
                // The total unique paths to this cell is the sum of both directions
                dpGrid.get(i).set(j, pathsFromTop + pathsFromLeft);
            }
        }
        
        // The final answer is waiting for us in the bottom-right corner!
        return dpGrid.get(m - 1).get(n - 1);
    }
}