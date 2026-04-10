class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        // Dual DSU memory banks: Microscopic memory footprint
        int[] parentA = new int[n + 1];
        int[] parentB = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            parentA[i] = i;
            parentB[i] = i;
        }
        
        int edgesUsed = 0;
        
        // We start with N isolated cities. 
        // A fully traversable graph must eventually drop to exactly 1 province.
        int aliceComponents = n;
        int bobComponents = n;
        
        // Pass 1: Greedily process Type 3 (Shared) edges FIRST.
        // Bypasses the massive O(E log E) Array.sort() completely!
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                // Try to union in Alice's graph
                boolean mergedA = union(parentA, edge[1], edge[2]);
                // Try to union in Bob's graph
                boolean mergedB = union(parentB, edge[1], edge[2]);
                
                // If it helped AT LEAST one of them, it's a critical edge
                if (mergedA || mergedB) {
                    edgesUsed++;
                    if (mergedA) aliceComponents--;
                    if (mergedB) bobComponents--;
                }
            }
        }
        
        // Pass 2: Process the individual Type 1 (Alice) and Type 2 (Bob) edges
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (union(parentA, edge[1], edge[2])) {
                    edgesUsed++;
                    aliceComponents--;
                }
            } else if (edge[0] == 2) {
                if (union(parentB, edge[1], edge[2])) {
                    edgesUsed++;
                    bobComponents--;
                }
            }
        }
        
        // THE CRITICAL CHECK: Are both players actually able to traverse everything?
        if (aliceComponents == 1 && bobComponents == 1) {
            // We safely kept 'edgesUsed'. Everything else is mathematically redundant garbage!
            return edges.length - edgesUsed;
        }
        
        return -1; // Someone is permanently stranded
    }
    
    // Universal Path Compression Engine
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
    
    // Instant hardware-level merge. Returns true ONLY if a new connection was forged.
    private boolean union(int[] parent, int i, int j) {
        int rootI = find(parent, i);
        int rootJ = find(parent, j);
        
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            return true;
        }
        
        return false; // They already report to the same boss!
    }
}