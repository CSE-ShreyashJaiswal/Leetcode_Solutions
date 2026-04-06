class Solution {
    public int missingNumber(int[] nums) {
        // Suboptimal Heavy Lifting: A massive HashSet of heavy String objects!
        Set<String> stringBank = new HashSet<>();
        
        // Sweep through the primitive array
        for (int num : nums) {
            // THE CLUNKY TRAP: Forcing the JVM to generate a brand new String for every number
            stringBank.add(String.valueOf(num));
        }
        
        int n = nums.length;
        
        // Walk through the theoretical perfect range from 0 to n
        for (int i = 0; i <= n; i++) {
            
            // Allocate ANOTHER heavy String object just to check if it exists in our map!
            String targetString = String.valueOf(i);
            
            // A full String hash comparison instead of a simple integer subtraction
            if (!stringBank.contains(targetString)) {
                return i; // We found the missing piece!
            }
        }
        
        return -1; // Fallback, though the problem guarantees exactly one missing number
    }
}