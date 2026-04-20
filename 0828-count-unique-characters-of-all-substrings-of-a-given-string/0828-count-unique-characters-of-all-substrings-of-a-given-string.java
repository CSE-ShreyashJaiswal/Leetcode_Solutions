class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        
        // The Bare-Metal Hardware Registers. 
        // We only track the absolute last two physical indices for the 26 uppercase letters.
        int[] lastSeen = new int[26];
        int[] prevSeen = new int[26];
        
        // Pre-fill with -1 to represent absolute null memory addresses
        Arrays.fill(lastSeen, -1);
        Arrays.fill(prevSeen, -1);
        
        int totalUniqueCount = 0;
        
        // A single contiguous sweep through the L1 cache
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            
            // THE MATHEMATICAL CUTOFF:
            // If we have seen this letter before, the CURRENT physical index 'i' 
            // acts as the impenetrable right-side wall for its previous occurrence!
            if (lastSeen[c] != -1) {
                // Contribution = (Distance to Left Wall) * (Distance to Right Wall)
                int leftDistance = lastSeen[c] - prevSeen[c];
                int rightDistance = i - lastSeen[c];
                
                totalUniqueCount += leftDistance * rightDistance;
            }
            
            // Shift the hardware registers forward to track the new physical layout
            prevSeen[c] = lastSeen[c];
            lastSeen[c] = i;
        }
        
        // THE TAIL FLUSH:
        // Any letter that appeared, its absolute final occurrence stretches 
        // all the way to the end of the string 'n' without ever hitting a duplicate!
        for (int c = 0; c < 26; c++) {
            if (lastSeen[c] != -1) {
                int leftDistance = lastSeen[c] - prevSeen[c];
                int rightDistance = n - lastSeen[c]; // The string boundary acts as the right wall
                
                totalUniqueCount += leftDistance * rightDistance;
            }
        }
        
        return totalUniqueCount;
    }
}