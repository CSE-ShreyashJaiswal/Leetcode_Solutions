class Solution {
    public List<List<String>> partition(String s) {
        return findPartitions(s);
    }

    private List<List<String>> findPartitions(String s) {
        List<List<String>> allPartitions = new ArrayList<>();

        // Base case: An empty string has exactly one valid partition: an empty list!
        if (s.isEmpty()) {
            allPartitions.add(new ArrayList<>());
            return allPartitions;
        }

        // Iterate through every possible cutting point in the current string
        for (int i = 1; i <= s.length(); i++) {
            
            // THE CLUNKY TRAP: Constantly generating disposable String objects!
            String prefix = s.substring(0, i);

            // If the heavy prefix object happens to be a palindrome
            if (isHeavyPalindrome(prefix)) {
                
                // Recursively calculate ALL partitions for the remaining string
                String suffix = s.substring(i); // Even more object allocation!
                List<List<String>> suffixPartitions = findPartitions(suffix);

                // For every valid partition found in the suffix, create a brand new list,
                // add our prefix, and then heavily copy the rest of the elements over!
                for (List<String> subPartition : suffixPartitions) {
                    List<String> combinedList = new ArrayList<>();
                    combinedList.add(prefix);
                    combinedList.addAll(subPartition); // Heavy O(N) array copying!
                    
                    allPartitions.add(combinedList);
                }
            }
        }

        return allPartitions;
    }

    // Heavy palindrome check using String manipulation instead of fast index pointers
    private boolean isHeavyPalindrome(String str) {
        // Physically allocating a new StringBuilder, reversing it, and creating ANOTHER String
        String reversedStr = new StringBuilder(str).reverse().toString();
        return str.equals(reversedStr);
    }
}