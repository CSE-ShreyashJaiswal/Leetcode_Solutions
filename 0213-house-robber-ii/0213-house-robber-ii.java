class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        
        // Suboptimal Heavy Lifting: Physically slicing and duplicating the arrays!
        List<Integer> streetOption1 = new ArrayList<>(); // Includes first house, skips the last
        List<Integer> streetOption2 = new ArrayList<>(); // Skips the first house, includes the last
        
        for (int i = 0; i < nums.length; i++) {
            // Auto-boxing overhead happening constantly here
            if (i != nums.length - 1) {
                streetOption1.add(nums[i]);
            }
            if (i != 0) {
                streetOption2.add(nums[i]);
            }
        }
        
        // Run our heavy DP logic on both separate physical lists
        int maxHaul1 = robLinearStreet(streetOption1);
        int maxHaul2 = robLinearStreet(streetOption2);
        
        // The ultimate answer is the best outcome between the two scenarios
        return Math.max(maxHaul1, maxHaul2);
    }

    private int robLinearStreet(List<Integer> houses) {
        if (houses.size() == 0) return 0;
        if (houses.size() == 1) return houses.get(0);
        
        // Allocate a massive memory bank to track our DP states for this specific street
        List<Integer> dp = new ArrayList<>();
        
        // Base cases for the first two houses
        dp.add(houses.get(0));
        dp.add(Math.max(houses.get(0), houses.get(1)));
        
        // Walk down the rest of the street
        for (int i = 2; i < houses.size(); i++) {
            int skip = dp.get(i - 1);
            int rob = houses.get(i) + dp.get(i - 2);
            
            // Store the heavy Integer object
            dp.add(Math.max(skip, rob));
        }
        
        return dp.get(dp.size() - 1);
    }
}