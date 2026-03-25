class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        
        // Suboptimal: Allocating an entire O(N) memory array just to track jump counts
        int[] minJumps = new int[n];
        
        // Fill the array with a massive number to represent "unreachable" 
        // We use 100000 instead of Integer.MAX_VALUE to prevent overflow during addition
        Arrays.fill(minJumps, 100000);
        
        // Base case: It takes 0 jumps to reach the starting line!
        minJumps[0] = 0;
        
        // Walk through the array from left to right
        for (int i = 0; i < n; i++) {
            
            // Find the absolute furthest index we can jump to from this exact spot
            int furthestJump = Math.min(i + nums[i], n - 1);
            
            // The O(N^2) Trap: Iterate through EVERY possible landing spot in range
            for (int j = i + 1; j <= furthestJump; j++) {
                
                // Update the landing spot with the minimum jumps required to get there
                minJumps[j] = Math.min(minJumps[j], minJumps[i] + 1);
            }
        }
        
        // The final answer is waiting for us at the very end of our DP array
        return minJumps[n - 1];
    }
}