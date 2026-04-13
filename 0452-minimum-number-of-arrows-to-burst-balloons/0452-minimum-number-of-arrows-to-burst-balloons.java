class Solution {
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if (n == 0) return 0;
        
        // Step 1: The Hardware-Safe Greedy Sort
        // We MUST sort strictly by END times to maximize our arrow's forward coverage.
        // Using Integer.compare safely navigates the 32-bit integer extremes.
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        
        int arrows = 1;
        
        // Prime the engine: 
        // We aim our very first arrow at the absolute tail-end of the first balloon.
        int currentArrowPosition = points[0][1];
        
        // Step 2: A single contiguous sweep through the L1 cache
        for (int i = 1; i < n; i++) {
            
            // THE MISS CHECK:
            // Does this next balloon start strictly AFTER our current arrow's trajectory?
            if (points[i][0] > currentArrowPosition) {
                
                // We missed it. We are mathematically forced to pull another arrow from the quiver.
                arrows++;
                
                // Snap our aim to the absolute tail-end of THIS new balloon
                currentArrowPosition = points[i][1];
            }
            // If it starts before or exactly at our arrow position, it gets pierced automatically. 
            // We do absolutely nothing and let the loop continue.
        }
        
        return arrows;
    }
}