class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) return 0;
        
        // Step 1: The Greedy Timeline Sort
        // We MUST sort strictly by the END times to maximize free space on the timeline!
        // Using Integer.compare safely bypasses integer underflow traps.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int removeCount = 0;
        
        // Prime the engine: our timeline currently finishes at the end of the very first interval
        int currentEnd = intervals[0][1];
        
        // Step 2: A single contiguous sweep through the L1 cache
        for (int i = 1; i < n; i++) {
            
            // THE OVERLAP CHECK:
            // Does this new interval start before our current timeline boundary finishes?
            if (intervals[i][0] < currentEnd) {
                // Collision! We mathematically MUST delete this new interval.
                // Why? Because our 'currentEnd' belongs to an interval that finishes 
                // earlier (or at the exact same time), making it the strictly superior choice.
                removeCount++;
            } else {
                // The sequence is safe. 
                // Snap our timeline boundary forward to the end of this new interval.
                currentEnd = intervals[i][1];
            }
        }
        
        return removeCount;
    }
}