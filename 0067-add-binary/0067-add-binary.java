class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        
        // The maximum possible length is the max of the two strings, PLUS 1 for a final carry
        int maxLength = Math.max(a.length(), b.length());
        
        // The bare-metal memory block
        char[] result = new char[maxLength + 1];
        int k = maxLength; // Start writing from the absolute end of the array
        
        // A single contiguous sweep from right to left
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            
            // Extract the bits directly from string memory, converting char to int
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            
            // The bitwise modulo drops the current bit (0 or 1)
            result[k] = (char) ((sum % 2) + '0');
            k--;
            
            // The division extracts the carry to carry over to the next loop
            carry = sum / 2;
        }
        
        // If the absolute first slot wasn't used by a final carry, 'k' will be 0.
        // We instantiate the String skipping the unused 0th index to avoid leading nulls!
        return new String(result, k + 1, maxLength - k);
    }
}