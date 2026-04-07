class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int shiftCount = 0;
        
        // Keep shifting right until the prefixes perfectly match
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shiftCount++;
        }
        
        // Shift the common prefix back to its original position, padding with 0s
        return left << shiftCount;
    }
}