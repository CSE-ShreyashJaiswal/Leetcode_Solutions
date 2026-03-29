class Solution {
    public int minCut(String s) {
        int n = s.length();
        
        // Suboptimal Heavy Lifting: A massive nested List instead of a boolean[][] matrix
        List<List<Boolean>> isPalindrome = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(false); // Auto-boxing overhead happening thousands of times!
            }
            isPalindrome.add(row);
        }
        
        // Pre-compute all palindromes in the string
        for (int right = 0; right < n; right++) {
            for (int left = 0; left <= right; left++) {
                
                // If the outer characters match, and the inner string is either non-existent 
                // (length <= 2) OR is also a palindrome, mark it as true!
                if (s.charAt(left) == s.charAt(right) && 
                   (right - left <= 2 || isPalindrome.get(left + 1).get(right - 1))) {
                    
                    isPalindrome.get(left).set(right, true);
                }
            }
        }
        
        // Allocate another dynamic list to track the minimum cuts
        List<Integer> minCuts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            minCuts.add(i); // Worst case: we cut every single character (i cuts for length i+1)
        }
        
        // Calculate the minimum cuts using our heavy palindrome table
        for (int right = 0; right < n; right++) {
            
            // If the entire substring from the start is a palindrome, we need exactly 0 cuts!
            if (isPalindrome.get(0).get(right)) {
                minCuts.set(right, 0);
                continue;
            }
            
            // Otherwise, check all possible cut points before 'right'
            for (int left = 1; left <= right; left++) {
                
                // If the right side of the cut is a valid palindrome
                if (isPalindrome.get(left).get(right)) {
                    
                    int currentBest = minCuts.get(right);
                    int cutsBeforeLeft = minCuts.get(left - 1);
                    
                    // Update our memory bank with the best choice
                    minCuts.set(right, Math.min(currentBest, cutsBeforeLeft + 1));
                }
            }
        }
        
        // The final answer is waiting for us at the very end of our cuts list
        return minCuts.get(n - 1);
    }
}