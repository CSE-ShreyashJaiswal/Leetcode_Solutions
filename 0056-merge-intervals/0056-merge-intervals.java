class Solution {
    public int[][] merge(int[][] intervals) {
        // Base case: Zero or one interval requires zero processing
        if (intervals.length <= 1) {
            return intervals;
        }

        // Step 1: The Timeline Sort
        // We MUST sort by the starting edges. 
        // Using Integer.compare safely bypasses integer underflow traps compared to (a[0] - b[0]).
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        
        // Prime the engine by tracking the very first interval
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        // Step 2: The Linear Sweep
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // THE OVERLAP CHECK:
            // If the next event starts before our current event finishes...
            if (currentEnd >= nextStart) {
                // ...we fuse them together by taking the absolute maximum boundary!
                // We stretch the primitive array strictly in place.
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // The sequence broke. They are completely disjoint.
                // Snap our pointer to the new interval and commit it to our timeline list.
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        // Cast the dynamic list back down to a bare-metal 2D primitive array
        return merged.toArray(new int[merged.size()][]);
    }
}