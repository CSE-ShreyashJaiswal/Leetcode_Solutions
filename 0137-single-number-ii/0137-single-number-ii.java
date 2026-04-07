class Solution {
    public int singleNumber(int[] nums) {
        int singleNum = 0;
        
        // Iterate through all 32 bits of a standard integer
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            
            // Sweep through the array and count how many numbers have a '1' at this i-th position
            for (int num : nums) {
                // Shift the number right by i spaces, isolate the last bit using AND 1
                bitCount += (num >> i) & 1;
            }
            
            // If the total count is not a multiple of 3, our unique number is responsible!
            if (bitCount % 3 != 0) {
                // Use bitwise OR to physically place a '1' in that exact position of our result
                singleNum |= (1 << i);
            }
        }
        
        return singleNum;
    }
}