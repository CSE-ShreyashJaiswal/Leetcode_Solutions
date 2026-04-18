class MyQueue {
    // The Dual-Cylinder Memory Banks
    // Using standard Stack to fulfill strict LeetCode requirements, 
    // though in production we'd use ArrayDeque for zero-synchronization overhead.
    private Stack<Integer> input;
    private Stack<Integer> output;

    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }
    
    public void push(int x) {
        // Fast-path hardware write: O(1) buffer append
        input.push(x);
    }
    
    public int pop() {
        // Ensure the read-buffer is primed before extraction
        primeOutput();
        return output.pop();
    }
    
    public int peek() {
        // Ensure the read-buffer is primed before inspection
        primeOutput();
        return output.peek();
    }
    
    public boolean empty() {
        // The queue is only mathematically empty if BOTH buffers are dry
        return input.isEmpty() && output.isEmpty();
    }
    
    // THE AMORTIZED O(1) TRANSFER ENGINE
    private void primeOutput() {
        // We ONLY pay the transfer cost when the read-buffer is completely empty!
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                // Popping from input and pushing to output mathematically inverts LIFO to FIFO
                output.push(input.pop());
            }
        }
    }
}
