class SummaryRanges {
    // The Red-Black Memory Fuser: Maps strictly Start -> End
    private TreeMap<Integer, Integer> map;

    public SummaryRanges() {
        map = new TreeMap<>();
    }
    
    public void addNum(int value) {
        // Step 1: Binary Search the tree for the nearest left interval
        Integer leftStart = map.floorKey(value);
        
        // THE REDUNDANCY CHECK: 
        // If the value is already safely swallowed by an existing interval, instantly drop it!
        if (leftStart != null && map.get(leftStart) >= value) {
            return;
        }
        
        // Find the absolute closest interval to our right
        Integer rightStart = map.higherKey(value);
        
        // Evaluate the exact mathematical boundaries
        boolean mergeLeft = (leftStart != null && map.get(leftStart) == value - 1);
        boolean mergeRight = (rightStart != null && rightStart == value + 1);
        
        // Scenario 1: The value is a perfect bridge! It fuses the left and right intervals.
        if (mergeLeft && mergeRight) {
            // Stretch the left interval's end all the way to the right interval's end
            map.put(leftStart, map.get(rightStart));
            // Mathematically sever the old right interval
            map.remove(rightStart);
        } 
        // Scenario 2: It simply appends to the right tail of the left interval
        else if (mergeLeft) {
            map.put(leftStart, value);
        } 
        // Scenario 3: It prepends to the left head of the right interval
        else if (mergeRight) {
            // We must update the key, so we remove the old head and drop the new one
            map.put(value, map.get(rightStart));
            map.remove(rightStart);
        } 
        // Scenario 4: It lands completely in isolation
        else {
            map.put(value, value);
        }
    }
    
    public int[][] getIntervals() {
        // Fast-path hardware conversion:
        // Because we maintained a perfectly balanced Red-Black tree, 
        // we extract the sorted intervals in strictly O(K) time without sorting!
        int[][] res = new int[map.size()][2];
        int i = 0;
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[i][0] = entry.getKey();
            res[i][1] = entry.getValue();
            i++;
        }
        
        return res;
    }
}