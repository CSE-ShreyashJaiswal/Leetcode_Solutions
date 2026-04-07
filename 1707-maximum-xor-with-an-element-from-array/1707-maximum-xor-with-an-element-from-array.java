class Solution {
    
    // Suboptimal Heavy Lifting: Augmenting the TrieNode to store extra state at EVERY level!
    class TrieNode {
        TrieNode[] children = new TrieNode[2];
        
        // The Clunky Trap: This extra integer doubles the memory footprint of our entire tree
        int minValInSubtree = Integer.MAX_VALUE; 
    }

    private TrieNode root = new TrieNode();

    // Heavy insertion method that constantly updates the minimum values
    private void insert(int num) {
        TrieNode current = root;
        
        // Update the root's knowledge of the minimum value
        current.minValInSubtree = Math.min(current.minValInSubtree, num);
        
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            
            if (current.children[bit] == null) {
                current.children[bit] = new TrieNode();
            }
            
            current = current.children[bit];
            
            // Force the JVM to calculate and overwrite the minimum value on EVERY step down
            current.minValInSubtree = Math.min(current.minValInSubtree, num);
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        // Step 1: Blindly dump every single number into our augmented memory bank
        for (int num : nums) {
            insert(num);
        }

        int[] result = new int[queries.length];

        // Step 2: Process queries online without sorting them
        for (int q = 0; q < queries.length; q++) {
            int xi = queries[q][0];
            int limitMi = queries[q][1];

            // If the absolute lowest number in our ENTIRE array is strictly greater than 
            // the query's limit, it is mathematically impossible to find a valid pair.
            if (root.minValInSubtree > limitMi) {
                result[q] = -1;
                continue;
            }

            TrieNode current = root;
            int currentMaxXor = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (xi >> i) & 1;
                int targetBit = 1 - bit; // We desperately want the opposite bit to maximize XOR

                // THE CLUNKY CHECK: We can only take our ideal path if it exists AND 
                // the augmented minimum value constraint is legally satisfied!
                if (current.children[targetBit] != null && current.children[targetBit].minValInSubtree <= limitMi) {
                    
                    currentMaxXor |= (1 << i);
                    current = current.children[targetBit];
                    
                } else {
                    // We are forced to take the non-ideal matching bit.
                    // We don't need to check the limit constraint here because the earlier 
                    // root check guarantees AT LEAST one valid path exists.
                    current = current.children[bit];
                }
            }
            
            result[q] = currentMaxXor;
        }

        return result;
    }
}