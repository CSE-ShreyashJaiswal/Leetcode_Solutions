class Solution {
    public boolean lemonadeChange(int[] bills) {
        // Primitive hardware counters. Zero object allocation.
        int fives = 0;
        int tens = 0;
        
        // A single contiguous sweep through the array
        for (int bill : bills) {
            if (bill == 5) {
                // Exact fare. Add it to the vault.
                fives++;
            } 
            else if (bill == 10) {
                // We MUST have at least one $5 bill to proceed.
                if (fives == 0) {
                    return false;
                }
                fives--;
                tens++;
            } 
            else { // bill == 20
                // GREEDY BARE-METAL LOGIC: 
                // Prioritize burning a $10 and a $5 to protect our versatile $5 reserves.
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } 
                // Fallback: Burn three $5 bills if we have no tens.
                else if (fives >= 3) {
                    fives -= 3;
                } 
                // Total mathematical bankruptcy.
                else {
                    return false; 
                }
            }
        }
        
        // If we survived the entire queue without returning false, we succeed!
        return true;
    }
}