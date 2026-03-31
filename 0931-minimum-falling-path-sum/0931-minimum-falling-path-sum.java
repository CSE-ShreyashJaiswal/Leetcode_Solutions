class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        
        // Suboptimal Heavy Lifting: A massive nested List instead of O(1) in-place primitive updates
        List<List<Integer>> dpGrid = new ArrayList<>();
        
        // Build the heavy 2D memory bank
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    // Base case: The top row's falling sum is just the value of the cell itself
                    row.add(matrix[0][j]); 
                } else {
                    row.add(0); // Auto-boxing overhead happening thousands of times!
                }
            }
            dpGrid.add(row);
        }
        
        // Walk through the rest of the grid starting from the second row
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                // Look strictly Top, Top-Left, and Top-Right in our heavy list
                int top = dpGrid.get(i - 1).get(j);
                
                // If we are at the left edge, there is no Top-Left path
                int topLeft = (j > 0) ? dpGrid.get(i - 1).get(j - 1) : Integer.MAX_VALUE;
                
                // If we are at the right edge, there is no Top-Right path
                int topRight = (j < n - 1) ? dpGrid.get(i - 1).get(j + 1) : Integer.MAX_VALUE;
                
                // Find the absolute cheapest path that could have dropped into this cell
                int cheapestPreviousDrop = Math.min(top, Math.min(topLeft, topRight));
                
                // Store the current cell's value plus the cheapest drop path
                dpGrid.get(i).set(j, cheapestPreviousDrop + matrix[i][j]);
            }
        }
        
        // The final answer could be anywhere in the very bottom row!
        int minPathSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            int currentBottomValue = dpGrid.get(n - 1).get(j);
            if (currentBottomValue < minPathSum) {
                minPathSum = currentBottomValue;
            }
        }
        return minPathSum;
    }
}