class MyHashMap {
    // The Bare-Metal Memory Node for Separate Chaining
    class Node {
        int key;
        int value;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // The Sparse Memory Bank: A prime number to mathematically shatter hash collisions
    private final int BUCKETS = 2069;
    private Node[] map;

    public MyHashMap() {
        map = new Node[BUCKETS];
    }
    
    // The Hardware-Level Hash Function
    private int hash(int key) {
        return key % BUCKETS;
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        
        // Scenario 1: The bucket is completely empty. Drop the node in directly.
        if (map[index] == null) {
            map[index] = new Node(key, value);
            return;
        }
        
        // Scenario 2: Collision detected! Traverse the chain.
        Node current = map[index];
        while (true) {
            // THE OVERWRITE RULE: If the key already exists, update its value in-place
            if (current.key == key) {
                current.value = value;
                return;
            }
            // If we hit the absolute tail of the chain, append the new node
            if (current.next == null) {
                current.next = new Node(key, value);
                return;
            }
            // Advance the pointer
            current = current.next;
        }
    }
    
    public int get(int key) {
        int index = hash(key);
        Node current = map[index];
        
        // Sweep the chain inside the specific bucket
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        
        // Cache miss
        return -1;
    }
    
    public void remove(int key) {
        int index = hash(key);
        Node current = map[index];
        
        // If the bucket is completely empty, there is nothing to sever
        if (current == null) return;
        
        // Scenario 1: The target is the absolute head of the bucket
        if (current.key == key) {
            // Drop the head, point the bucket directly to the second node
            map[index] = current.next;
            return;
        }
        
        // Scenario 2: Traverse and surgically bypass the target node
        while (current.next != null) {
            if (current.next.key == key) {
                // Sever the physical link, dropping the target node into the Garbage Collector
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }
}