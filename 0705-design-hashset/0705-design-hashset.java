class MyHashSet {
    // The Bare-Metal Memory Bank. 
    // Size 31251 mathematically covers up to 1,000,031
    private int[] bitset;

    public MyHashSet() {
        bitset = new int[31251];
    }
    
    public void add(int key) {
        int bucket = key / 32;
        int bitIndex = key % 32;
        
        // THE HARDWARE INSERTION:
        // Create a mask with a '1' at the specific bitIndex.
        // Bitwise OR (|) forces that specific bit to 1 without touching the others.
        bitset[bucket] |= (1 << bitIndex);
    }
    
    public void remove(int key) {
        int bucket = key / 32;
        int bitIndex = key % 32;
        
        // THE HARDWARE SEVER:
        // Create a mask with a '1' at the specific bitIndex, then invert it (~) 
        // so it becomes all 1s except for a 0 at our target.
        // Bitwise AND (&) forces our target bit to 0 without touching the others.
        bitset[bucket] &= ~(1 << bitIndex);
    }
    
    public boolean contains(int key) {
        int bucket = key / 32;
        int bitIndex = key % 32;
        
        // THE HARDWARE PROBE:
        // Shift our specific bit all the way to the 0th position.
        // Bitwise AND (&) it with 1. If it is a 1, the key exists!
        return ((bitset[bucket] >> bitIndex) & 1) == 1;
    }
}