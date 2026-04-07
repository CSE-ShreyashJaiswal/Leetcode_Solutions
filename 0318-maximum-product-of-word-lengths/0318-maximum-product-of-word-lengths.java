class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        
        // Parallel arrays to cache the compressed bits and lengths. 
        // This completely avoids object allocation and method calls in the inner loop!
        int[] masks = new int[n];
        int[] lens = new int[n];
        
        // Step 1: Compress every string into a 26-bit integer footprint
        for (int i = 0; i < n; i++) {
            int bitmask = 0;
            String word = words[i];
            
            // Sweep through the string and flip the corresponding bits
            for (int j = 0; j < word.length(); j++) {
                // Instantly calculate the alphabetical offset and shift a '1' into place
                bitmask |= (1 << (word.charAt(j) - 'a'));
            }
            
            masks[i] = bitmask;
            lens[i] = word.length(); 
        }
        
        int maxProduct = 0;
        
        // Step 2: The hardware-level comparison loop
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                // If the bitwise AND is exactly 0, they share absolutely zero characters.
                // This replaces a massive O(L) string comparison with a 1-nanosecond logic gate!
                if ((masks[i] & masks[j]) == 0) {
                    
                    int currentProduct = lens[i] * lens[j];
                    if (currentProduct > maxProduct) {
                        maxProduct = currentProduct;
                    }
                }
            }
        }
        
        return maxProduct;
    }
}