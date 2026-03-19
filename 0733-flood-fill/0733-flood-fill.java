class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        
        // We use a heavy Set with serialized String keys to track exactly where we've been
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        
        // Start the BFS journey at the source pixel
        queue.add(new int[]{sr, sc});
        visited.add(sr + "," + sc);
        
        // Standard 4-directional movement arrays (Down, Up, Right, Left)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            
            // Physically change the pixel's color
            image[r][c] = color;
            
            // Explore all 4 adjacent neighbors
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Boundary checks to ensure we don't fall off the grid
                if (nr >= 0 && nr < image.length && nc >= 0 && nc < image[0].length) {
                    
                    String coordinateStr = nr + "," + nc;
                    
                    // If the neighbor matches the original color AND we haven't visited it yet
                    if (image[nr][nc] == originalColor && !visited.contains(coordinateStr)) {
                        visited.add(coordinateStr);
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return image;
    }
}