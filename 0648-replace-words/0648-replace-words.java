class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        // Suboptimal Heavy Lifting: A massive HashSet of Strings instead of a sleek Prefix Tree!
        Set<String> rootBank = new HashSet<>();
        for (String root : dictionary) {
            rootBank.add(root); 
        }
        
        // Shatter the entire sentence into thousands of disposable string objects
        String[] massiveWordArray = sentence.split(" ");
        
        // Allocate a dynamic list to hold our heavily processed strings
        List<String> processedWords = new ArrayList<>();
        
        for (String heavyWord : massiveWordArray) {
            String replacement = heavyWord; // Default to the original word if no root is found
            
            // THE CLUNKY TRAP: Forcing the JVM to generate a brand new String object 
            // for EVERY single theoretical prefix length!
            for (int i = 1; i <= heavyWord.length(); i++) {
                
                // Physically severing the string to create a heavy comparison object
                String theoreticalRoot = heavyWord.substring(0, i);
                
                // A full String hash comparison instead of a fast primitive pointer check
                if (rootBank.contains(theoreticalRoot)) {
                    replacement = theoreticalRoot; // We found the shortest possible root!
                    break; 
                }
            }
            
            // Dump the final string into our dynamic memory bank
            processedWords.add(replacement);
        }
        
        // Force the JVM to sweep through the massive list and stitch it all back together
        return String.join(" ", processedWords);
    }
}