class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> powerSet = new ArrayList<>();
        int n = nums.length;
        
        // Suboptimal: Using Math.pow instead of a lightning-fast bit shift (1 << n)
        int totalSubsets = (int) Math.pow(2, n);
        
        for (int i = 0; i < totalSubsets; i++) {
            
            // HEAVY LIFTING: Convert the integer to a raw binary string
            String binaryStr = Integer.toBinaryString(i);
            
            // The Garbage Trap: Manually pad with leading zeros using String concatenation!
            // This creates a brand new, disposable String object in memory on every single loop.
            while (binaryStr.length() < n) {
                binaryStr = "0" + binaryStr; 
            }
            
            List<Integer> currentSubset = new ArrayList<>();
            
            // Iterate through the characters of our padded string
            for (int j = 0; j < n; j++) {
                
                // Check the physical characters instead of using fast bitwise AND operations
                if (binaryStr.charAt(j) == '1') {
                    // Auto-boxing overhead: primitive nums[j] is converted to an Integer object
                    currentSubset.add(nums[j]);
                }
            }
            
            powerSet.add(currentSubset);
        }
        
        return powerSet;
    }
}