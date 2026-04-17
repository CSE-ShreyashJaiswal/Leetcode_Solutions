class Solution {
    public boolean isNumber(String s) {
        // The Bare-Metal State Machine Registers
        boolean seenDigit = false;
        boolean seenExponent = false;
        boolean seenDot = false;
        
        // A single contiguous sweep through the L1 cache
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                // We registered a valid digit
                seenDigit = true;
            } 
            else if (c == '+' || c == '-') {
                // Sign is only valid at the absolute beginning, 
                // or strictly after an 'e' or 'E'
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } 
            else if (c == 'e' || c == 'E') {
                // Exponent is only valid if we haven't seen one yet, 
                // AND we have seen a base digit
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                
                // THE CRITICAL RESET:
                // We absolutely MUST have a valid integer AFTER the 'e'.
                // We reset seenDigit to false to force the remaining string to prove itself!
                seenDigit = false;
            } 
            else if (c == '.') {
                // Decimals cannot appear after an exponent or another decimal
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } 
            else {
                // Mathematical garbage detected (letters, symbols, spaces)
                return false;
            }
        }
        
        // A valid number mathematically requires at least one digit at the end of the evaluation
        return seenDigit;
    }
}