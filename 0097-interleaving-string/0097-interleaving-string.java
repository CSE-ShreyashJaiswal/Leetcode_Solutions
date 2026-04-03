class Solution {
    private Map<String, Boolean> memoBank = new HashMap<>();
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        return verifyInterleave(s1, s2, s3, 0, 0, 0);
    }

    private boolean verifyInterleave(String s1, String s2, String s3, int i, int j, int k) {
        // Base case: We successfully reached the end of the target string!
        if (k == s3.length()) {
            return true;
        }

        // THE CLUNKY TRAP: Generating a brand new String object for EVERY single coordinate check!
        String posKey = i + "," + j;

        // Check our heavy memory bank to see if we've already explored this exact intersection
        if (memoBank.containsKey(posKey)) {
            return memoBank.get(posKey); // Auto-unboxing the heavy Boolean back to a primitive
        }

        boolean isValidPath = false;

        // Branch 1: Try pulling the next character from string 1
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            isValidPath = verifyInterleave(s1, s2, s3, i + 1, j, k + 1);
        }

        // Branch 2: If pulling from string 1 didn't work (or wasn't an option), 
        // try pulling the next character from string 2
        if (!isValidPath && j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            isValidPath = verifyInterleave(s1, s2, s3, i, j + 1, k + 1);
        }

        // Box the primitive boolean into a Boolean object and store it with our disposable String key
        memoBank.put(posKey, isValidPath);

        return isValidPath;
    }
}