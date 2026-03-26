class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        // Suboptimal: Allocating an entire O(N) memory array just to track future states
        int[] maxFuturePrice = new int[n];
        
        // Pass 1: Work backwards to figure out the absolute highest price available AFTER day i
        maxFuturePrice[n - 1] = prices[n - 1]; // The last day's best future price is just itself
        
        for (int i = n - 2; i >= 0; i--) {
            // The best future price is either the price today, or the best price from tomorrow onwards
            maxFuturePrice[i] = Math.max(prices[i], maxFuturePrice[i + 1]);
        }
        
        int maxProfit = 0;
        
        // Pass 2: Walk forwards and check the difference between today's price and our pre-calculated future
        for (int i = 0; i < n; i++) {
            int potentialProfit = maxFuturePrice[i] - prices[i];
            
            // If this profit beats our current record, save it!
            if (potentialProfit > maxProfit) {
                maxProfit = potentialProfit;
            }
        }
        
        return maxProfit;
    }
}