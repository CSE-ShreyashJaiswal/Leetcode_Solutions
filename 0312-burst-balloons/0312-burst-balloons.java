class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        // Step 1: The Padded Memory Bank
        // We create a strictly bounded primitive array to hold the virtual boundaries
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        
        // Step 2: The DP Matrix
        // dp[i][j] represents the maximum coins collected by bursting ALL balloons strictly between index i and j.
        int[][] dp = new int[n + 2][n + 2];
        
        // Step 3: The Interval Expansion Engine
        // 'len' is the size of the interval we are currently evaluating
        for (int len = 1; len <= n; len++) {
            
            // Sweep the starting boundary 'left'
            for (int left = 1; left <= n - len + 1; left++) {
                
                int right = left + len - 1;
                
                // THE REVERSE TIMELINE:
                // Assume balloon 'k' is the absolute LAST balloon to be burst in this [left, right] window.
                for (int k = left; k <= right; k++) {
                    
                    // The coins gained by bursting 'k' last:
                    // Since it's the last to go, its direct neighbors are the rigid boundaries left-1 and right+1.
                    int coins = arr[left - 1] * arr[k] * arr[right + 1];
                    
                    // Add the recursively independent max coins from the left and right territories
                    int totalCoins = coins + dp[left][k - 1] + dp[k + 1][right];
                    
                    // Lock in the absolute maximum yield for this specific window
                    dp[left][right] = Math.max(dp[left][right], totalCoins);
                }
            }
        }
        
        // The final answer represents bursting everything strictly between virtual index 0 and n+1
        return dp[1][n];
    }
}