class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();
        int n = nums.length;
        
        // Base case: Absolute zero allocation for empty arrays
        if (n == 0) return ranges;
        
        int start = nums[0];
        
        // A single contiguous sweep through the L1 cache
        for (int i = 0; i < n; i++) {
            
            // THE TRIGGER CONDITION: 
            // We only build a string if we hit the absolute end of the array,
            // OR if the next number mathematically breaks the +1 sequence.
            if (i == n - 1 || nums[i] + 1 != nums[i + 1]) {
                
                if (start == nums[i]) {
                    // It's a lone number. Use String.valueOf to bypass concatenation overhead.
                    ranges.add(String.valueOf(start));
                } else {
                    // It's a range. We allocate exactly one String object for this entire sequence.
                    // Using a direct StringBuilder is the most bare-metal way to format this in Java.
                    StringBuilder sb = new StringBuilder();
                    sb.append(start).append("->").append(nums[i]);
                    ranges.add(sb.toString());
                }
                
                // Immediately snap the start pointer to the next sequence
                if (i != n - 1) {
                    start = nums[i + 1];
                }
            }
        }
        
        return ranges;
    }
}