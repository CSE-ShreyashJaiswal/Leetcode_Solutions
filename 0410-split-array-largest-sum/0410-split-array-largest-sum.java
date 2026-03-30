class Solution {
    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;
        
        // Define the search space bounds
        for (int num : nums) {
            left = Math.max(left, num); // The largest single element
            right += num;               // The sum of all elements
        }
        
        // Binary search for the optimal largest sum
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // If we can successfully split the array with this max sum, 
            // try to find an even smaller valid sum.
            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                // If we can't, the allowed sum is too small. We need a larger sum.
                left = mid + 1;
            }
        }
        
        return left;
    }

    private boolean canSplit(int[] nums, int k, int targetMaxSum) {
        int currentSubarraySum = 0;
        int splitsRequired = 1;
        
        for (int num : nums) {
            // If adding the current number exceeds our target, we MUST make a split
            if (currentSubarraySum + num > targetMaxSum) {
                currentSubarraySum = num; // Start a new subarray with the current number
                splitsRequired++;
                
                // If we've exceeded our allowed number of splits, this target sum is invalid
                if (splitsRequired > k) {
                    return false;
                }
            } else {
                // Otherwise, safely add the number to the current subarray
                currentSubarraySum += num;
            }
        }
        
        return true;
    }
}