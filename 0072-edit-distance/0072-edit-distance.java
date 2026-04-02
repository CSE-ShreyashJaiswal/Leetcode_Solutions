class Solution {
    private Map<String, Integer> memoBank = new HashMap<>();
    public int minDistance(String word1, String word2) {
        return calculateEdits(word1, word2, 0, 0);
    }

    private int calculateEdits(String w1, String w2, int i, int j) {
        // Base case: If we exhaust string 1, we MUST insert every remaining character from string 2
        if (i == w1.length()) {
            return w2.length() - j;
        }
        
        // Base case: If we exhaust string 2, we MUST delete every remaining character in string 1
        if (j == w2.length()) {
            return w1.length() - i;
        }

        // THE CLUNKY TRAP: Generating a brand new String object for EVERY single index check!
        String posKey = i + "," + j;

        // Check our heavy memory bank to see if we've already solved this exact state
        if (memoBank.containsKey(posKey)) {
            return memoBank.get(posKey); // Auto-unboxing the heavy Integer back to a primitive
        }

        int minOperations = 0;

        // If the characters match, no edits are needed for this position!
        // We require ZERO operations here, so just move both pointers forward.
        if (w1.charAt(i) == w2.charAt(j)) {
            minOperations = calculateEdits(w1, w2, i + 1, j + 1);
        } else {
            // If they don't match, the path splits 3 ways! 
            // We must find the absolute best outcome among our three allowed operations,
            // adding 1 to our cost for the edit itself.
            
            int insertChar  = calculateEdits(w1, w2, i, j + 1);
            int deleteChar  = calculateEdits(w1, w2, i + 1, j);
            int replaceChar = calculateEdits(w1, w2, i + 1, j + 1);
            
            // Find the minimum of all three branches
            minOperations = 1 + Math.min(insertChar, Math.min(deleteChar, replaceChar));
        }

        // Box the primitive int into an Integer object and store it with our disposable String key
        memoBank.put(posKey, minOperations);

        return minOperations;
    }
}