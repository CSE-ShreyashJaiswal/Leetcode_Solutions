class Solution {
    public int calculate(String s) {
        int length = s.length();
        if (length == 0) return 0;
        
        // The Bare-Metal ALU Registers. Zero object allocation!
        int result = 0;
        int lastNumber = 0;
        int currentNumber = 0;
        char operation = '+'; // Default primer
        
        // A single contiguous sweep through the L1 cache
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            
            // Mathematically shift the digits into our current number
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            
            // If we hit an operator, OR if we hit the absolute end of the string,
            // it is time to execute the buffered mathematical operation.
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                
                if (operation == '+') {
                    // Commit the previous safe number to the global result
                    result += lastNumber;
                    // Buffer the new number
                    lastNumber = currentNumber;
                } 
                else if (operation == '-') {
                    result += lastNumber;
                    // Buffer the new number as a negative
                    lastNumber = -currentNumber;
                } 
                else if (operation == '*') {
                    // HIGHEST PRIORITY: Instantly intercept and modify the buffer!
                    lastNumber = lastNumber * currentNumber;
                } 
                else if (operation == '/') {
                    // HIGHEST PRIORITY: Instantly intercept and modify the buffer!
                    lastNumber = lastNumber / currentNumber;
                }
                
                // Reset the active reader for the next sequence
                operation = currentChar;
                currentNumber = 0;
            }
        }
        
        // Commit the final buffered number to the global result
        result += lastNumber;
        
        return result;
    }
}