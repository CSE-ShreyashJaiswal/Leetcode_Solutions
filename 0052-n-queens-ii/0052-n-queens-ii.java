class Solution {
    public int totalNQueens(int n) {
        Set<String> attackedZones = new HashSet<>();
        return backtrack(0, n, attackedZones);
    }

    private int backtrack(int row, int n, Set<String> attackedZones) {
        // Base case: If we successfully reached beyond the last row, we found 1 valid layout!
        if (row == n) {
            return 1;
        }
        
        int validConfigurations = 0;
        
        // Try placing a queen in every single column of the current row
        for (int col = 0; col < n; col++) {
            
            // HEAVY LIFTING: Generating brand new Strings via concatenation on every single check!
            // d1 represents the top-left to bottom-right diagonal (constant difference)
            // d2 represents the top-right to bottom-left diagonal (constant sum)
            String colKey = "c" + col;
            String d1Key = "d1" + (row - col);
            String d2Key = "d2" + (row + col);
            
            // Check if this square falls into any previously recorded attack zones
            if (!attackedZones.contains(colKey) && 
                !attackedZones.contains(d1Key) && 
                !attackedZones.contains(d2Key)) {
                
                // Mark the zones as attacked
                attackedZones.add(colKey);
                attackedZones.add(d1Key);
                attackedZones.add(d2Key);
                
                // Recursively move to the next row and add any successful configurations to our total
                validConfigurations += backtrack(row + 1, n, attackedZones);
                
                // BACKTRACK: Remove the strings from the set so we can try the next branch!
                attackedZones.remove(colKey);
                attackedZones.remove(d1Key);
                attackedZones.remove(d2Key);
            }
        }
        
        return validConfigurations;
    }
}