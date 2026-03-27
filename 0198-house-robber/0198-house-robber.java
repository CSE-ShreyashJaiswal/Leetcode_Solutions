class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        
        // Suboptimal Heavy Lifting: Dump the sleek primitive array into a dynamic list
        List<Integer> houses = new ArrayList<>();
        for (int num : nums) {
            houses.add(num); // Auto-boxing overhead happening here!
        }
        
        // Allocate a massive O(N) memory bank to track our DP states
        List<Integer> dp = new ArrayList<>();
        
        // Base cases for the first two houses
        dp.add(houses.get(0));
        dp.add(Math.max(houses.get(0), houses.get(1)));
        
        // Walk down the rest of the street
        for (int i = 2; i < houses.size(); i++) {
            
            // Option 1: We skip this house, meaning we keep whatever we had at the previous house
            int skip = dp.get(i - 1);
            
            // Option 2: We rob this house, meaning we add its value to whatever we had 2 houses ago
            int rob = houses.get(i) + dp.get(i - 2);
            
            // Store the absolute best choice in our memory bank
            dp.add(Math.max(skip, rob));
        }
        
        // The maximum possible haul is waiting for us at the very end of our DP list
        return dp.get(dp.size() - 1);
    }
}