class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false;
        
        // Single-pass L1 cache sweep for character constraints
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        
        // Calculate the absolute baseline of missing required types
        int missingTypes = (hasLower ? 0 : 1) + (hasUpper ? 0 : 1) + (hasDigit ? 0 : 1);
        
        // DOMAIN 1: Hardware Underrun
        if (n < 6) {
            return Math.max(missingTypes, 6 - n);
        }
        
        // The Memory Registers for Sequence Tracking
        int replaceCount = 0;
        int oneDeletes = 0; // Sequences where length % 3 == 0
        int twoDeletes = 0; // Sequences where length % 3 == 1
        
        // Sweep for repeating sequence clusters
        for (int i = 2; i < n; i++) {
            if (password.charAt(i) == password.charAt(i - 1) && password.charAt(i - 1) == password.charAt(i - 2)) {
                int length = 2;
                while (i < n && password.charAt(i) == password.charAt(i - 1)) {
                    length++;
                    i++;
                }
                
                // Base calculation for how many replacements this cluster requires
                replaceCount += length / 3;
                
                // Categorize the cluster into our modulo deletion priorities
                if (length % 3 == 0) oneDeletes++;
                else if (length % 3 == 1) twoDeletes++;
            }
        }
        
        // DOMAIN 2: Hardware Sweet Spot
        if (n <= 20) {
            return Math.max(missingTypes, replaceCount);
        }
        
        // DOMAIN 3: Hardware Overrun
        int deleteCount = n - 20;
        
        // Execute the Greedy Modulo Protocol:
        // Priority 1: Use 1 delete to save 1 replace
        replaceCount -= Math.min(deleteCount, oneDeletes);
        
        // Priority 2: Use 2 deletes to save 1 replace
        replaceCount -= Math.min(Math.max(deleteCount - oneDeletes, 0), twoDeletes * 2) / 2;
        
        // Priority 3: Use 3 deletes to save 1 replace on anything remaining
        replaceCount -= Math.max(deleteCount - oneDeletes - twoDeletes * 2, 0) / 3;
        
        // The final sum: The forced deletes + whatever replacements we couldn't optimize away
        return deleteCount + Math.max(missingTypes, replaceCount);
    }
}