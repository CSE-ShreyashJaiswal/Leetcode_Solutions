class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;
        
        for (int num : nums) {
            // Every number > 1 is guaranteed to at least be divisible by 1 and itself
            int currentSum = 1 + num;
            int divisorCount = 2;
            
            // We only need to check up to the square root of the number!
            for (int d = 2; d * d <= num; d++) {
                
                // We found a clean divisor
                if (num % d == 0) {
                    
                    if (d * d == num) {
                        // It's a perfect square, so 'd' is a single, distinct divisor
                        divisorCount++;
                        currentSum += d;
                    } else {
                        // We found a pair of divisors: 'd' and 'num / d'
                        divisorCount += 2;
                        currentSum += d + (num / d);
                    }
                    
                    // THE BARE-METAL OPTIMIZATION: 
                    // If we exceed 4 divisors, it mathematically fails. 
                    // Instantly kill the loop to save massive CPU cycles!
                    if (divisorCount > 4) {
                        break; 
                    }
                }
            }
            
            // If the dust settles and we have exactly 4, commit it to the total
            if (divisorCount == 4) {
                totalSum += currentSum;
            }
        }
        
        return totalSum;
    }
}