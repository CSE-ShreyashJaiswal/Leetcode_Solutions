class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;

        // Heavy Pass 1: Collect ALL land coordinates into memory first
        List<int[]> allLand = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    allLand.add(new int[]{r, c});
                }
            }
        }

        // We use a heavy Set with serialized String keys to track exactly where we've been
        Set<String> visited = new HashSet<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Pass 2: Iterate through our collected land and launch a BFS for new islands
        for (int[] land : allLand) {
            int r = land[0];
            int c = land[1];
            String coordStr = r + "," + c;

            if (!visited.contains(coordStr)) {
                islands++; // We found a piece of land we haven't mapped yet!
                
                // Launch a clunky BFS using an ArrayList instead of a proper Queue
                List<int[]> clunkyQueue = new ArrayList<>();
                clunkyQueue.add(new int[]{r, c});
                visited.add(coordStr);

                while (!clunkyQueue.isEmpty()) {
                    // Removing from index 0 forces the entire array to shift left!
                    int[] current = clunkyQueue.remove(0);
                    int currR = current[0];
                    int currC = current[1];

                    // Explore all 4 adjacent neighbors
                    for (int[] dir : directions) {
                        int nextR = currR + dir[0];
                        int nextC = currC + dir[1];
                        String nextStr = nextR + "," + nextC;

                        // Boundary checks to ensure we don't fall off the grid
                        if (nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols) {
                            // If it's land AND we haven't visited it yet
                            if (grid[nextR][nextC] == '1' && !visited.contains(nextStr)) {
                                visited.add(nextStr);
                                clunkyQueue.add(new int[]{nextR, nextC});
                            }
                        }
                    }
                }
            }
        }

        return islands;
    }
}