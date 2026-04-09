class Solution {
    public int islandPerimeter(int[][] grid) {
        int landBlocks = 0;
        int internalEdges = 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // A single, contiguous sweep across the matrix memory block
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                
                if (grid[r][c] == 1) {
                    landBlocks++;
                    
                    // Look Right: Is there a neighbor?
                    if (c < cols - 1 && grid[r][c + 1] == 1) {
                        internalEdges++;
                    }
                    
                    // Look Down: Is there a neighbor?
                    if (r < rows - 1 && grid[r + 1][c] == 1) {
                        internalEdges++;
                    }
                }
            }
        }
        
        // Every block brings 4 borders, every shared connection hides 2 of them!
        return (landBlocks * 4) - (internalEdges * 2);
    }
}