class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        // Base case: 1 or 2 points mathematically form exactly 1 line
        if (n <= 2) return n;
        
        int globalMax = 0;
        
        // Step 1: Anchor on every single point
        for (int i = 0; i < n; i++) {
            // Memory Bank: Maps our custom 32-bit slope ID to the frequency count
            Map<Integer, Integer> slopeCount = new HashMap<>();
            int localMax = 0;
            
            // Step 2: Sweep all subsequent points to form radiating lines
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                // NORMALIZATION PROTOCOL: Force the vector into a consistent direction
                if (dx == 0) {
                    dy = 1; // Pure vertical line
                } else if (dy == 0) {
                    dx = 1; // Pure horizontal line
                } else {
                    // Reduce the fraction to its absolute base prime vectors
                    int gcd = gcd(Math.abs(dx), Math.abs(dy));
                    dx /= gcd;
                    dy /= gcd;
                    
                    // Force the vector to always point 'forward' on the X-axis
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }
                }
                
                // THE BARE-METAL HARDWARE PACKING:
                // Shift strictly positive 'dx' into the upper 16 bits.
                // Bitwise AND 'dy' with 0xFFFF to protect against negative sign bleeding, 
                // fusing it into the lower 16 bits.
                int slopeId = (dx << 16) | (dy & 0xFFFF);
                
                // Log the primitive ID into our frequency map
                int count = slopeCount.getOrDefault(slopeId, 0) + 1;
                slopeCount.put(slopeId, count);
                
                localMax = Math.max(localMax, count);
            }
            
            // +1 accounts for the anchor point 'i' that all these lines radiate from
            globalMax = Math.max(globalMax, localMax + 1);
        }
        
        return globalMax;
    }
    
    // Euclid's algorithm: Pure hardware-level modulo math
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
