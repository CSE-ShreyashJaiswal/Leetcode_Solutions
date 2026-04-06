class Solution {
    
    // A bare-metal node: exactly two pointers (0 and 1)
    class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }
    
    private TrieNode root = new TrieNode();

    public int findMaximumXOR(int[] nums) {
        
        // Step 1: Build the Trie using raw bit extraction
        for (int num : nums) {
            TrieNode current = root;
            
            // Integers in Java are 32 bits. We evaluate from MSB (bit 31) down to LSB (bit 0)
            for (int i = 31; i >= 0; i--) {
                // Extract the bit at the i-th position (will be exactly 0 or 1)
                int bit = (num >> i) & 1;
                
                if (current.children[bit] == null) {
                    current.children[bit] = new TrieNode();
                }
                current = current.children[bit];
            }
        }

        int maxOverallXor = 0;

        // Step 2: Traverse the Trie to find the maximum possible XOR
        for (int num : nums) {
            TrieNode current = root;
            int currentMaxXor = 0;
            
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                
                // To maximize XOR, we desperately want the OPPOSITE bit (1^0 = 1, 0^1 = 1)
                int targetBit = 1 - bit; 
                
                if (current.children[targetBit] != null) {
                    // We found the opposite bit! This guarantees a '1' in our XOR result at this position.
                    // We flip the i-th bit to '1' in our running total.
                    currentMaxXor = currentMaxXor | (1 << i);
                    current = current.children[targetBit];
                } else {
                    // We are forced to take the matching bit, meaning this position contributes a '0'.
                    current = current.children[bit];
                }
            }
            
            maxOverallXor = Math.max(maxOverallXor, currentMaxXor);
        }

        return maxOverallXor;
    }
}