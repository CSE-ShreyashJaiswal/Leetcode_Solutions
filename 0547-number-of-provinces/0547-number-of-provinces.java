class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        
        // The bare-metal DSU engine: A single contiguous array in memory
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Initially, every city is its own boss
        }
        
        int provinces = n;

        // Since the matrix is symmetric, we only need to scan the top-right triangle
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                if (isConnected[i][j] == 1) {
                    // Find the absolute top-level boss of both cities
                    int rootI = find(parent, i);
                    int rootJ = find(parent, j);
                    
                    // If they have different bosses, they are in different provinces. Merge them!
                    if (rootI != rootJ) {
                        parent[rootI] = rootJ;
                        provinces--; // Two provinces just became one
                    }
                }
            }
        }
        
        return provinces;
    }

    // The Path Compression Engine: Blazing fast O(α(N)) lookups
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        // As we search upward, we forcefully reassign the current node directly to the absolute boss.
        // This flattens the tree and makes all future lookups practically O(1).
        return parent[i] = find(parent, parent[i]); 
    }
}