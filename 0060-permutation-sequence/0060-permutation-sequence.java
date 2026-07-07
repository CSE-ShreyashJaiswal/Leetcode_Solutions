class Solution {
    public String getPermutation(int n, int k) {
        // The strictly sized primitive memory bank
        char[] nums = new char[n];
        
        // Prime the array with the absolute lowest lexicographical state (e.g., "1234")
        for (int i = 0; i < n; i++) {
            nums[i] = (char) ('1' + i);
        }
        
        // Physically mutate the primitive array k-1 times to reach the target state
        for (int step = 1; step < k; step++) {
            nextPermutation(nums);
        }
        
        // Instantiate exactly ONE string at the very end
        return new String(nums);
    }
    
    // The Bare-Metal Lexicographical Engine
    private void nextPermutation(char[] nums) {
        int i = nums.length - 2;
        
        // Step 1: Sweep right-to-left to find the first character that breaks the descending order
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            int j = nums.length - 1;
            // Step 2: Sweep right-to-left again to find the smallest character strictly greater than nums[i]
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // Step 3: Swap them directly in physical memory
            swap(nums, i, j);
        }
        
        // Step 4: Reverse the remaining suffix to reset it to the lowest possible order
        reverse(nums, i + 1, nums.length - 1);
    }
    
    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(char[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}