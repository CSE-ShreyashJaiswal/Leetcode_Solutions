import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        // Suboptimal Heavy Lifting: A massive nested List generated through pure brute-force math
        List<List<Integer>> triangle = new ArrayList<>();
        
        // Build the triangle row by row
        for (int i = 0; i < numRows; i++) {
            List<Integer> currentRow = new ArrayList<>();
            
            // Calculate the exact mathematical combination for every single cell
            for (int j = 0; j <= i; j++) {
                
                // THE CLUNKY TRAP: Forcing massive BigInteger factorial calculations 
                // instead of simply adding the two numbers from the previous row!
                BigInteger nFact = calculateFactorial(i);
                BigInteger rFact = calculateFactorial(j);
                BigInteger nMinusRFact = calculateFactorial(i - j);
                
                // nCr = n! / (r! * (n-r)!)
                BigInteger denominator = rFact.multiply(nMinusRFact);
                BigInteger cellValue = nFact.divide(denominator);
                
                // Downcast back to a standard Integer and auto-box it into our list
                currentRow.add(cellValue.intValue());
            }
            
            triangle.add(currentRow);
        }
        
        return triangle;
    }
    
    // Heavy Memory Allocation: Generating thousands of disposable BigInteger objects
    private BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        
        // Physically recalculating the factorial from scratch every single time this is called
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        
        return result;
    }
}