class Solution {
    public char findTheDifference(String s, String t) {
        char missingChar = 0;
        int n = s.length();
        
        // Sweep through both strings simultaneously
        for (int i = 0; i < n; i++) {
            missingChar ^= s.charAt(i);
            missingChar ^= t.charAt(i);
        }
        
        // String t has exactly one extra character at the very end
        missingChar ^= t.charAt(n);
        
        return missingChar;
    }
}