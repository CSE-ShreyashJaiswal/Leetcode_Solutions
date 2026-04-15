class Solution {
    public int reverse(int x) {
        // The purely 32-bit memory bank
        int rev = 0;
        
        while (x != 0) {
            // Hardware-level extraction of the right-most digit
            int pop = x % 10;
            // Shift the original integer right mathematically
            x /= 10;
            
            // THE PRE-EMPTIVE OVERFLOW CHECKS:
            // Max Value: 2147483647. If rev > 214748364, the next step overflows.
            // If it equals 214748364, it only overflows if the new digit is greater than 7.
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            
            // Min Value: -2147483648. If rev < -214748364, the next step underflows.
            // If it equals -214748364, it only underflows if the new digit is less than -8.
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            
            // It is mathematically safe to shift and push
            rev = rev * 10 + pop;
        }
        
        return rev;
    }
}