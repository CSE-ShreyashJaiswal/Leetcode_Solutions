class Solution {
    public int lengthOfLIS(int[] nums) {
        
    if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Suboptimal Heavy Lifting: Dump the sleek primitive array into a dynamic list
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num); // Auto-boxing overhead happening here!
        }
        
        // Allocate a massive memory bank to track the LIS ending at every single index
        List<Integer> dpBank = new ArrayList<>();
        
        // Base case: Every individual number is an increasing subsequence of at least length 1
        for (int i = 0; i < nums.length; i++) {
            dpBank.add(1); 
        }
        
        int overallMax = 1;
        
        // Walk through the array starting from the second element
        for (int i = 1; i < numList.size(); i++) {
            
            // The O(N^2) Trap: Look backwards at EVERY single number before this one
            for (int j = 0; j < i; j++) {
                
                // If the historical number is strictly smaller, we can extend its sequence!
                if (numList.get(j) < numList.get(i)) {
                    
                    // Look up the best sequence length we built at index j
                    int previousBest = dpBank.get(j);
                    int currentBest = dpBank.get(i);
                    
                    // Update our bank with the absolute best option
                    dpBank.set(i, Math.max(currentBest, previousBest + 1));
                }
            }
            
            // Track the longest sequence we've seen anywhere in the array
            overallMax = Math.max(overallMax, dpBank.get(i));
        }
        
        return overallMax;
    }
}