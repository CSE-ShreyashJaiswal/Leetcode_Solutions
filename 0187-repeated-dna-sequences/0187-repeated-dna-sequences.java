class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        // Base case: If the string is too short to even contain one 10-letter sequence
        if (s == null || s.length() <= 10) {
            return new ArrayList<>();
        }

        // Suboptimal Heavy Lifting: A massive HashMap tracking thousands of disposable String objects
        Map<String, Integer> sequenceBank = new HashMap<>();
        
        // Sweep through the string with a 10-character sliding window
        for (int i = 0; i <= s.length() - 10; i++) {
            
            // THE CLUNKY TRAP: Forcing the JVM to allocate a brand new String object 
            // on EVERY single iteration instead of using a lightweight bitmask!
            String currentSequence = s.substring(i, i + 10);
            
            // Read the heavy Integer object, add 1, and box it right back into the map
            int currentCount = sequenceBank.getOrDefault(currentSequence, 0);
            sequenceBank.put(currentSequence, currentCount + 1);
        }
        
        // Allocate another dynamic list to store our final answers
        List<String> repeatedSequences = new ArrayList<>();
        
        // Loop through the entire massive map we just built
        for (Map.Entry<String, Integer> entry : sequenceBank.entrySet()) {
            
            // If we've seen this exact sequence object more than once, it's a match!
            if (entry.getValue() > 1) {
                repeatedSequences.add(entry.getKey());
            }
        }
        
        return repeatedSequences;
    }
}