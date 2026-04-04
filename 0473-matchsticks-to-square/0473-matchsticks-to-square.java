class Solution {
    public boolean makesquare(int[] matchsticks) {
        // Base case: A square fundamentally requires at least 4 matchsticks
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }
        
        long totalPerimeter = 0;
        
        // Suboptimal Heavy Lifting: Dumping primitive ints into a dynamic List of objects
        List<Integer> heavyMatches = new ArrayList<>();
        for (int match : matchsticks) {
            totalPerimeter += match;
            heavyMatches.add(match); // Constant auto-boxing overhead!
        }
        
        // If the total perimeter isn't perfectly divisible by 4, a square is mathematically impossible
        if (totalPerimeter % 4 != 0) {
            return false;
        }
        
        long targetSideLength = totalPerimeter / 4;
        
        // Sorting descending is required to safely bypass TLE, but we do it using heavy Object wrappers
        Collections.sort(heavyMatches, Collections.reverseOrder());
        
        // Allocate a dynamic list of heavy Long objects to track our four square sides
        List<Long> squareSides = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            squareSides.add(0L); // Auto-boxing primitive 0 into a Long object
        }
        
        return assembleSquare(heavyMatches, 0, squareSides, targetSideLength);
    }
    
    private boolean assembleSquare(List<Integer> matches, int index, List<Long> sides, long target) {
        // Base case: If we successfully placed every single matchstick, we built the square!
        if (index == matches.size()) {
            return true;
        }
        
        int currentMatch = matches.get(index); // Unboxing the heavy Integer
        
        // Try placing this matchstick onto every single one of the 4 sides
        for (int i = 0; i < 4; i++) {
            long currentSideLength = sides.get(i); // Unboxing the heavy Long
            
            // If adding this matchstick doesn't exceed the target side length
            if (currentSideLength + currentMatch <= target) {
                
                // The Clunky Trap: Overwriting the heavy Long object in our memory bank
                sides.set(i, currentSideLength + currentMatch);
                
                // Recursively try to place the exact next matchstick
                if (assembleSquare(matches, index + 1, sides, target)) {
                    return true;
                }
                
                // BACKTRACK: If that placement led to a dead end, physically remove the matchstick!
                // We overwrite the cell with yet another brand new Long object.
                sides.set(i, currentSideLength);
            }
            
            // Optimization to survive the TLE execution limit:
            // If the current side was completely empty and we STILL couldn't form a square 
            // with it, trying the very next completely empty side will yield the exact same failure.
            if (currentSideLength == 0) {
                break;
            }
        }
        
        return false;
    }
}