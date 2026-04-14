class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        
        // We MUST return a List of Integers. To bypass heavy ArrayList resizing and shifting, 
        // we pre-allocate a static array and populate it directly via index.
        Integer[] result = new Integer[n];
        
        // The Fenwick Tree memory bank. Size 20002 covers -10000 to 10000 (shifted by 10001).
        // Index 0 is ignored as BIT strictly requires 1-based indexing.
        int[] tree = new int[20002];
        int offset = 10001;
        
        // A single contiguous sweep from right to left
        for (int i = n - 1; i >= 0; i--) {
            int shiftedNum = nums[i] + offset;
            
            // THE QUERY: Instantly calculate the sum of all elements strictly smaller (shiftedNum - 1)
            int smallerCount = 0;
            for (int j = shiftedNum - 1; j > 0; j -= (j & -j)) {
                smallerCount += tree[j];
            }
            
            // Lock the answer into our pre-allocated array
            result[i] = smallerCount;
            
            // THE UPDATE: Blast the current number up the Fenwick Tree
            for (int j = shiftedNum; j < 20002; j += (j & -j)) {
                tree[j]++;
            }
        }
        
        // Wrap the static array into a sleek List in O(1) time
        return Arrays.asList(result);
    }
}