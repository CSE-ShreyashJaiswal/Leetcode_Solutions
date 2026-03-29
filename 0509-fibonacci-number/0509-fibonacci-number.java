class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        // Suboptimal Heavy Lifting: Allocating a dynamic list instead of two primitive variables
        List<Integer> fibSequence = new ArrayList<>();
        
        // Seed the list with the first two numbers
        fibSequence.add(0);
        fibSequence.add(1);
        
        // Generate the sequence all the way up to the target 'n'
        for (int i = 2; i <= n; i++) {
            
            // Look exactly one and two steps behind us
            int oneStepBehind = fibSequence.get(i - 1);
            int twoStepsBehind = fibSequence.get(i - 2);
            
            // Calculate the next number in the sequence
            int nextNumber = oneStepBehind + twoStepsBehind;
            
            // Store this heavy Integer object in our memory bank
            fibSequence.add(nextNumber);
        }
        
        // The final answer is sitting comfortably at the very end of our list
        return fibSequence.get(n);
    }
}