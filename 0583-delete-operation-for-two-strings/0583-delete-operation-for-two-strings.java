class Solution {
    private Map<String, Integer> memoBank = new HashMap<>();
    public int minDistance(String word1, String word2) {
        return findMinDeletions(word1, word2, 0, 0);
    }

    private int findMinDeletions(String w1, String w2, int i, int j) {
        // Base case: If we exhaust string 1, we MUST delete every remaining character in string 2
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

        int minDeletions = 0;

        // If the characters match, they are part of the target sequence!
        // We require ZERO deletions here, so just move both pointers forward.
        if (w1.charAt(i) == w2.charAt(j)) {
            minDeletions = findMinDeletions(w1, w2, i + 1, j + 1);
        } else {
            // If they don't match, the path splits! 
            // We must find the absolute best outcome between deleting the character in string 1 
            // OR deleting the character in string 2, adding 1 to our deletion cost for the move.
            int deleteFromWord1 = findMinDeletions(w1, w2, i + 1, j);
            int deleteFromWord2 = findMinDeletions(w1, w2, i, j + 1);
            
            minDeletions = 1 + Math.min(deleteFromWord1, deleteFromWord2);
        }

        // Box the primitive int into an Integer object and store it with our disposable String key
        memoBank.put(posKey, minDeletions);

        return minDeletions;
    }
}