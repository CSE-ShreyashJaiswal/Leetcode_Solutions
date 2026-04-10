class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        
        // The bare-metal DSU engine: Mapping array indices instead of massive Strings!
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; 
        }
        
        // We start by assuming every single string is in its own isolated group
        int totalGroups = n;
        
        // Sweep through every unique pair in the array
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                // If the strings are similar, attempt a hardware-level merge
                if (isSimilar(strs[i], strs[j])) {
                    
                    int rootI = find(parent, i);
                    int rootJ = find(parent, j);
                    
                    if (rootI != rootJ) {
                        parent[rootI] = rootJ;
                        totalGroups--; // Two isolated groups just became one!
                    }
                }
            }
        }
        
        return totalGroups;
    }
    
    // The Hardware-Level Comparison Engine
    private boolean isSimilar(String a, String b) {
        int diffCount = 0;
        
        for (int i = 0; i < a.length(); i++) {
            // Read straight from memory. Zero object allocation!
            if (a.charAt(i) != b.charAt(i)) {
                diffCount++;
                
                // THE BARE-METAL OPTIMIZATION: 
                // A valid swap only fixes exactly 2 differing positions. 
                // The millisecond we hit 3 differences, we instantly kill the loop!
                if (diffCount > 2) {
                    return false;
                }
            }
        }
        
        // If we survived the loop, they differ by exactly 0 or 2 positions.
        return true;
    }
    
    // Path compression keeps the hierarchy perfectly flat for O(1) lookups
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
}