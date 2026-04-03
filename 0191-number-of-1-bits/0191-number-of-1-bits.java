class Solution {
    public int hammingWeight(int n) {
        String binaryString = Integer.toBinaryString(n);
        
        // THE CLUNKY TRAP: Shattering the string into an array of disposable String objects
        String[] stringBits = binaryString.split("");
        
        // Dump the heavy string objects into a dynamic memory bank
        List<String> bitBank = new ArrayList<>();
        for (String bit : stringBits) {
            // .split("") can sometimes produce an empty string at the first index
            if (!bit.isEmpty()) {
                bitBank.add(bit);
            }
        }
        
        int setBitsCount = 0;
        
        // Walk through our massive list of String objects
        for (String heavyBit : bitBank) {
            
            // Using a full String comparison instead of a lightning-fast bit mask!
            if (heavyBit.equals("1")) {
                setBitsCount++;
            }
        }
        
        return setBitsCount;
    }
}