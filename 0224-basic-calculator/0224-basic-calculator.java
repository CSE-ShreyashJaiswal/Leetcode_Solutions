class Solution {
    public int calculate(String s) {
        // The Bare-Metal Hardware Stack. 
        // We bypass Stack<Integer> completely to avoid auto-boxing overhead!
        int[] stack = new int[s.length()];
        int top = 0; // Our hardware stack pointer
        
        // The ALU Registers
        int result = 0;
        int currentNumber = 0;
        int sign = 1; // 1 represents positive (+), -1 represents negative (-)
        
        // A single contiguous sweep through the L1 cache
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                // Mathematically shift the digits into our current number buffer
                currentNumber = (currentNumber * 10) + (c - '0');
            } 
            else if (c == '+') {
                // Lock in the previous number and prepare for a positive addition
                result += sign * currentNumber;
                currentNumber = 0;
                sign = 1;
            } 
            else if (c == '-') {
                // Lock in the previous number and prepare for a subtraction
                result += sign * currentNumber;
                currentNumber = 0;
                sign = -1;
            } 
            else if (c == '(') {
                // CONTEXT SWITCH:
                // We are entering a parenthesis. Save the current global state to our primitive stack!
                stack[top++] = result;
                stack[top++] = sign;
                
                // Reset the ALU for the isolated inner-parenthesis calculation
                result = 0;
                sign = 1;
            } 
            else if (c == ')') {
                // Lock in the absolute final number inside the parenthesis
                result += sign * currentNumber;
                currentNumber = 0;
                
                // CONTEXT RESTORE:
                // Pop the old sign and the old result from the hardware stack
                int poppedSign = stack[--top];
                int poppedResult = stack[--top];
                
                // Apply the saved sign to our inner calculation, then merge it back into the saved result
                result = poppedResult + (poppedSign * result);
            }
        }
        
        // Commit any lingering buffered number to the final global result
        result += sign * currentNumber;
        
        return result;
    }
}