class FreqStack {
    // Memory Bank 1: Tracks the absolute frequency count of every element
    private Map<Integer, Integer> freq;
    
    // Memory Bank 2: A hardware-like tier system. 
    // Maps a specific frequency level -> Stack of elements that reached this level
    private Map<Integer, Stack<Integer>> group;
    
    // The global register tracking the highest active frequency tier
    private int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int val) {
        // Retrieve and increment the element's current frequency
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);
        
        // If this element just broke the global frequency record, push the limit up
        if (f > maxFreq) {
            maxFreq = f;
        }
        
        // Push the value strictly into its new frequency tier
        group.computeIfAbsent(f, k -> new Stack<>()).push(val);
    }
    
    public int pop() {
        // Instant O(1) retrieval from the absolute highest frequency tier
        Stack<Integer> maxFreqStack = group.get(maxFreq);
        int val = maxFreqStack.pop();
        
        // Mathematically degrade the popped element's global frequency
        freq.put(val, freq.get(val) - 1);
        
        // If the highest frequency tier is now completely empty, 
        // the global limit mathematically drops by exactly 1.
        if (maxFreqStack.isEmpty()) {
            maxFreq--;
        }
        
        return val;
    }
}