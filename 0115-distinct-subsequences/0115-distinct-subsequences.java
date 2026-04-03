class Solution {
        private Map<String, Integer> memoBank = new HashMap<>();
    public int numDistinct(String s, String t) {
        return countSubsequences(s, t, 0, 0);
    }

    private int countSubsequences(String s, String t, int i, int j) {
        // Base case: We successfully matched every single character in the target string!
        // This counts as exactly 1 valid distinct subsequence.
        if (j == t.length()) {
            return 1;
        }
        
        // Base case: We ran out of characters in the source string BEFORE finishing the target.
        // This path is a dead end.
        if (i == s.length()) {
            return 0;
        }

        // THE CLUNKY TRAP: Generating a brand new String object for EVERY single index check!
        String posKey = i + "," + j;

        // Check our heavy memory bank to see if we've already explored this exact intersection
        if (memoBank.containsKey(posKey)) {
            return memoBank.get(posKey); // Auto-unboxing the heavy Integer back to a primitive
        }

        int distinctWays = 0;

        // If the characters match, the timeline splits! 
        if (s.charAt(i) == t.charAt(j)) {
            // Option 1: We USE this matching character and advance both pointers.
            int useCharacter = countSubsequences(s, t, i + 1, j + 1);
            
            // Option 2: We IGNORE this matching character and see if we can find 
            // another match later in the source string.
            int skipCharacter = countSubsequences(s, t, i + 1, j);
            
            distinctWays = useCharacter + skipCharacter;
        } else {
            // If they don't match, we have no choice but to skip the character in the source 
            // string and keep looking.
            distinctWays = countSubsequences(s, t, i + 1, j);
        }

        // Box the primitive int into an Integer object and store it with our disposable String key
        memoBank.put(posKey, distinctWays);

        return distinctWays;
    }
}