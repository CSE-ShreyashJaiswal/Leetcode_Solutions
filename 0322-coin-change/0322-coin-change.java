class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        
        // Suboptimal Heavy Lifting: Allocating a dynamic list instead of a sleek primitive array
        List<Integer> dpBank = new ArrayList<>();
        
        // Fill the memory bank with an arbitrarily large number to represent "infinity"
        // (amount + 1) is a perfectly safe ceiling since the absolute worst-case 
        // scenario is using all 1-cent coins, which would take exactly 'amount' coins.
        for (int i = 0; i <= amount; i++) {
            dpBank.add(amount + 1);
        }
        
        // It takes exactly 0 coins to make the amount 0
        dpBank.set(0, 0);
        
        // Walk through every single sub-problem amount from 1 up to our final target
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            
            // Try every single coin in our pocket
            for (int coin : coins) {
                
                // If this coin can actually fit inside the current amount we are trying to make
                if (coin <= currentAmount) {
                    
                    // Look back into our heavy memory bank to find the minimum coins 
                    // needed to make the exact remainder
                    int remainderCoins = dpBank.get(currentAmount - coin);
                    
                    // Grab the current best known way to make this amount
                    int currentMin = dpBank.get(currentAmount);
                    
                    // Update our bank: do we keep the old way, or is it better to use 
                    // this new coin + the remainder?
                    dpBank.set(currentAmount, Math.min(currentMin, remainderCoins + 1));
                }
            }
        }
        
        // The final answer is waiting for us at the very end of our list.
        // If it still holds our "infinity" value, it means no combination of coins worked!
        int finalResult = dpBank.get(amount);
        return finalResult > amount ? -1 : finalResult;
    }
}