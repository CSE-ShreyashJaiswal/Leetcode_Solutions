class Solution {

    private Map<String, Integer> memoBank = new HashMap<>();
    public int longestPalindromeSubseq(String s) {
        return findLPS(s, 0, s.length() - 1);
    }

    private int findLPS(String s, int left, int right) {
        // Base case: The pointers crossed each other, meaning there is no string left
        if (left > right) {
            return 0;
        }
        
        // Base case: The pointers crashed into the exact same character. 
        // A single standalone character is always a valid palindrome of length 1!
        if (left == right) {
            return 1;
        }

        // THE CLUNKY TRAP: Generating a brand new String object for EVERY single index check!
        String posKey = left + "," + right;

        // Check our heavy memory bank to see if we've already solved this exact substring
        if (memoBank.containsKey(posKey)) {
            return memoBank.get(posKey); // Auto-unboxing the heavy Integer back to a primitive
        }

        int maxLength = 0;

        // If the outer characters match, they form the outer shell of a palindrome!
        // We instantly add 2 to our length and move BOTH pointers inward.
        if (s.charAt(left) == s.charAt(right)) {
            maxLength = 2 + findLPS(s, left + 1, right - 1);
        } else {
            // If they don't match, the path splits! 
            // We must find the absolute best outcome between skipping the left character 
            // OR skipping the right character.
            int skipLeft = findLPS(s, left + 1, right);
            int skipRight = findLPS(s, left, right - 1);
            
            maxLength = Math.max(skipLeft, skipRight);
        }

        // Box the primitive int into an Integer object and store it with our disposable String key
        memoBank.put(posKey, maxLength);

        return maxLength;
    }
}