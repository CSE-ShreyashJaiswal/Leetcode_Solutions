class Solution {
    public int minCostClimbingStairs(int[] cost) {
        List<Integer> dpBank = new ArrayList<>();
        
        // Base cases: The problem states you can start at either step 0 or step 1
        dpBank.add(cost[0]);
        dpBank.add(cost[1]);
        
        // Walk up the rest of the stairs
        for (int i = 2; i < cost.length; i++) {
            
            // Look exactly one and two steps behind us
            int oneStepBehind = dpBank.get(i - 1);
            int twoStepsBehind = dpBank.get(i - 2);
            
            // The cost to stand on THIS step is the cost of the step itself, 
            // PLUS the absolute cheapest path it took to get here.
            int currentCost = cost[i] + Math.min(oneStepBehind, twoStepsBehind);
            
            // Store this heavy Integer object in our memory bank
            dpBank.add(currentCost);
        }
        
        // The very top of the floor is essentially one step past the end of the array.
        // You can reach it by jumping from either the very last step, or the second-to-last step.
        int n = dpBank.size();
        return Math.min(dpBank.get(n - 1), dpBank.get(n - 2));
    }
}