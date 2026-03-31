class Solution {
        private Map<String, Integer> memoBank = new HashMap<>();
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Dump the sleek primitive array into a massive nested List of heavy Objects
        List<List<Integer>> heavyGrid = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(matrix[i][j]); // Constant auto-boxing overhead!
            }
            heavyGrid.add(row);
        }
        
        int overallLongestPath = 0;
        
        // Initiate a heavy recursive search from EVERY single cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int currentPathLength = dfs(heavyGrid, i, j, -1);
                overallLongestPath = Math.max(overallLongestPath, currentPathLength);
            }
        }
        
        return overallLongestPath;
    }

    private int dfs(List<List<Integer>> grid, int row, int col, int previousValue) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // Base case: If we step out of bounds, the path length is 0
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 0;
        }
        
        // Base case: The path MUST be strictly increasing!
        int currentValue = grid.get(row).get(col);
        if (currentValue <= previousValue) {
            return 0;
        }
        
        // THE CLUNKY TRAP: Generating a brand new String object for EVERY single coordinate check!
        String posKey = row + "," + col;
        
        // Check if we've already done the heavy lifting for this exact cell
        if (memoBank.containsKey(posKey)) {
            return memoBank.get(posKey);
        }
        
        // Recursively explore all 4 physical directions (Up, Down, Left, Right)
        int pathUp    = dfs(grid, row - 1, col, currentValue);
        int pathDown  = dfs(grid, row + 1, col, currentValue);
        int pathLeft  = dfs(grid, row, col - 1, currentValue);
        int pathRight = dfs(grid, row, col + 1, currentValue);
        
        // Find the absolute longest path among all 4 directions
        int maxDirection = Math.max(Math.max(pathUp, pathDown), Math.max(pathLeft, pathRight));
        
        // The longest path starting from THIS cell is 1 (the cell itself) + the best future path
        int longestFromHere = 1 + maxDirection;
        
        // Store the heavy Integer object in our map using our dynamically generated String key
        memoBank.put(posKey, longestFromHere);
        
        return longestFromHere;
    }
}