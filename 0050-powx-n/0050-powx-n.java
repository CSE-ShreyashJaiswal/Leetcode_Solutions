class Solution {
    public double myPow(double x, int n) {
        // Base case shortcut
        if (n == 0) return 1.0;
        
        // THE 64-BIT SAFE HARBOR:
        // We catch 'n' in a long to safely handle the absolute value of Integer.MIN_VALUE
        long N = n;
        
        // Handle negative exponents mathematically
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double ans = 1.0;
        
        // THE BARE-METAL BINARY EXPONENTIATION ENGINE
        while (N > 0) {
            
            // If the lowest bit is a 1 (meaning the current power is odd),
            // we multiply our current base into the final answer.
            if ((N & 1) == 1) {
                ans *= x;
            }
            
            // Mathematically square the base (x^2, x^4, x^8...)
            x *= x;
            
            // Bitwise right-shift the exponent (equivalent to N /= 2, but executes faster at the ALU level)
            N >>= 1;
        }
        
        return ans;
    }
}