class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSideLength = 0;
        
        // Suboptimal Heavy Lifting: A massive nested List instead of a 1D primitive array
        List<List<Integer>> dpGrid = new ArrayList<>();
        
        // Build the heavy 2D memory bank and initialize with 0s
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0); // Auto-boxing overhead happening thousands of times!
            }
            dpGrid.add(row);
        }
        
        // Walk through the entire physical matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // We only care about expanding squares if the current cell is a '1'
                if (matrix[i][j] == '1') {
                    
                    // Base case: If we are on the top row or left column, the biggest square 
                    // we can form ending here is exactly size 1
                    if (i == 0 || j == 0) {
                        dpGrid.get(i).set(j, 1);
                    } else {
                        // Look at the heavy objects Top, Left, and Top-Left
                        int top = dpGrid.get(i - 1).get(j);
                        int left = dpGrid.get(i).get(j - 1);
                        int topLeft = dpGrid.get(i - 1).get(j - 1);
                        
                        // The maximum square we can form is strictly bottlenecked by the 
                        // smallest square touching us from those three directions!
                        int bottleneck = Math.min(top, Math.min(left, topLeft));
                        
                        dpGrid.get(i).set(j, bottleneck + 1);
                    }
                    
                    // Constantly track the largest side length we have discovered so far
                    int currentSquareSide = dpGrid.get(i).get(j);
                    maxSideLength = Math.max(maxSideLength, currentSquareSide);
                }
            }
        }
        
        // The problem asks for the AREA of the square, so we square the side length!
        return maxSideLength * maxSideLength;
    }
}