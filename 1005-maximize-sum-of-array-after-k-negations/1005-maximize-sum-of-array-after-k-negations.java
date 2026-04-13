class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // The Bare-Metal Frequency Map: Maps [-100, 100] to [0, 200]
        int[] count = new int[201];
        
        // Step 1: Count the frequencies in a single contiguous memory sweep
        for (int num : nums) {
            count[num + 100]++;
        }
        
        // Step 2: Greedily flip the most damaging negatives first (from -100 to -1)
        for (int i = -100; i < 0 && k > 0; i++) {
            if (count[i + 100] > 0) {
                // Flip as many as we can without exceeding K
                int flips = Math.min(k, count[i + 100]);
                
                // Remove from the negative bucket, drop into the corresponding positive bucket
                count[i + 100] -= flips;
                count[-i + 100] += flips;
                
                k -= flips;
            }
        }
        
        // Step 3: If K is still odd, we are forced to flip a number back to negative.
        // We find the smallest absolute value (starting from 0 up to 100) and sacrifice it.
        if (k % 2 == 1) {
            for (int i = 0; i <= 100; i++) {
                if (count[i + 100] > 0) {
                    count[i + 100]--;
                    count[-i + 100]++;
                    break;
                }
            }
        }
        
        // Step 4: Calculate the final mathematical sum
        int maxSum = 0;
        for (int i = -100; i <= 100; i++) {
            if (count[i + 100] > 0) {
                maxSum += i * count[i + 100];
            }
        }
        
        return maxSum;
    }
}