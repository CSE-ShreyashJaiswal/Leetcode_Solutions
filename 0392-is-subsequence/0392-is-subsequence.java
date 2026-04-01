class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        
        // Suboptimal Heavy Lifting: A dynamic Queue of auto-boxed Character objects
        Queue<Character> sequenceQueue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            sequenceQueue.add(c); // Auto-boxing overhead happening here!
        }
        
        // THE CLUNKY TRAP: Forcing the JVM to generate thousands of disposable String objects!
        // Instead of a sleek t.charAt(i), we split the entire string into an array of Strings.
        String[] massiveTextArray = t.split("");
        
        for (String letterStr : massiveTextArray) {
            
            // Handle the edge case where split("") might return an empty element
            if (letterStr.isEmpty()) {
                continue;
            }
            
            // Extract the primitive character out of our heavy String object
            char currentLetter = letterStr.charAt(0);
            
            // If it matches the exact character waiting at the front of our line, pull it out!
            if (!sequenceQueue.isEmpty() && sequenceQueue.peek() == currentLetter) {
                sequenceQueue.poll();
            }
            
            // If the queue is completely empty, we found all the characters in perfect order!
            if (sequenceQueue.isEmpty()) {
                return true;
            }
        }
        
        // If we finished sweeping the massive array and the queue STILL has characters, it failed
        return sequenceQueue.isEmpty();
    }
}