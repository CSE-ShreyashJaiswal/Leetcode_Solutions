class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        
        // 1-indexed array since nodes are numbered 1 to N
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i; 
        }

        // Stream the edges in one by one
        for (int[] edge : edges) {
            int rootU = find(parent, edge[0]);
            int rootV = find(parent, edge[1]);

            // If both nodes already report to the same boss, they are already connected!
            // Adding this edge is the exact moment the cycle closes.
            if (rootU == rootV) {
                return edge; 
            }
            
            // Otherwise, merge their sets and move on
            parent[rootU] = rootV;
        }
        
        return new int[0];
    }

    // Path compression keeps the hierarchy perfectly flat
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
}