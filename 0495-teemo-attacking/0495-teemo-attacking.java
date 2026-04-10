class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        
        // Base case: Zero attacks means zero poison
        if (n == 0) return 0;
        
        int totalPoisonTime = 0;
        
        // A single contiguous sweep through the L1 cache
        for (int i = 0; i < n - 1; i++) {
            // THE BARE-METAL OPTIMIZATION:
            // The poison lasts either the full duration, OR it gets cut short 
            // by the exact mathematical difference before the next attack hits.
            int timeUntilNextAttack = timeSeries[i + 1] - timeSeries[i];
            
            // Instantly add whichever is smaller using a hardware-level conditional evaluation
            totalPoisonTime += Math.min(timeUntilNextAttack, duration);
        }
        
        // The loop stops before the final attack. 
        // The absolute last attack ALWAYS applies its full, uninterrupted duration!
        totalPoisonTime += duration;
        
        return totalPoisonTime;
    }
}