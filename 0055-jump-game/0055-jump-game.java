class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        
        // Suboptimal: Allocating an entire O(N) memory array just to track states
        boolean[] canReachEnd = new boolean[n];
        
        // Base case: The last index can obviously reach itself!
        canReachEnd[n - 1] = true;
        
        // Work backwards from the second-to-last element all the way to the start
        for (int i = n - 2; i >= 0; i--) {
            
            // Find the absolute furthest index we can jump to from this exact spot
            int furthestJump = Math.min(i + nums[i], n - 1);
            
            // The O(N^2) Trap: Iterate through EVERY possible landing spot in range
            for (int j = i + 1; j <= furthestJump; j++) {
                
                // If a landing spot is known to reach the end, then our current spot can too!
                if (canReachEnd[j]) {
                    canReachEnd[i] = true;
                    break; // We found a valid path, no need to check shorter jumps
                }
            }
        }
        
        // The final answer is simply whether the starting line has a valid path recorded
        return canReachEnd[0];
    }
}