class Solution {
    public int reverseBits(int n) {
        int result = 0;
        
        // An integer in Java is strictly 32 bits
        for (int i = 0; i < 32; i++) {
            
            // Step 1: Shift our result left by 1 to make room for the new bit
            result <<= 1;
            
            // Step 2: Extract the rightmost bit of 'n' using a bitwise AND mask, 
            // and use bitwise OR to drop it directly into the empty slot of 'result'
            result |= (n & 1);
            
            // Step 3: Shift 'n' to the right by 1 to process the next bit.
            // CRITICAL: We MUST use the unsigned logical right shift (>>>) 
            // so it fills the left side with 0s, ignoring the sign!
            n >>>= 1;
        }
        
        return result;
    }
}