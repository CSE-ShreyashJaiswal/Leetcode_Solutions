class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length;
        if (n == 0) return new ArrayList<>();
        
        // Boot up the recursive Divide & Conquer engine
        return mergeSortSkyline(buildings, 0, n - 1);
    }

    private List<List<Integer>> mergeSortSkyline(int[][] buildings, int lo, int hi) {
        List<List<Integer>> res = new ArrayList<>();
        
        // Base Case: A single building mathematically creates exactly two skyline points
        // Point 1: [Left Edge, Height]
        // Point 2: [Right Edge, Ground Level (0)]
        if (lo == hi) {
            res.add(Arrays.asList(buildings[lo][0], buildings[lo][2]));
            res.add(Arrays.asList(buildings[lo][1], 0));
            return res;
        }

        int mid = lo + (hi - lo) / 2;
        
        // Recursively fetch the skylines for both halves
        List<List<Integer>> leftSkyline = mergeSortSkyline(buildings, lo, mid);
        List<List<Integer>> rightSkyline = mergeSortSkyline(buildings, mid + 1, hi);

        // Zip them together
        return mergeSkylines(leftSkyline, rightSkyline);
    }

    // The O(N) Mathematical Zip
    private List<List<Integer>> mergeSkylines(List<List<Integer>> left, List<List<Integer>> right) {
        List<List<Integer>> merged = new ArrayList<>();
        
        // Track the current "active" height of both skylines
        int currentLeftHeight = 0;
        int currentRightHeight = 0;
        
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            int currentX;
            int maxActiveHeight;
            
            List<Integer> point1 = left.get(i);
            List<Integer> point2 = right.get(j);

            // Move the pointer that sits further to the left on the X-axis
            if (point1.get(0) < point2.get(0)) {
                currentX = point1.get(0);
                currentLeftHeight = point1.get(1);
                maxActiveHeight = Math.max(currentLeftHeight, currentRightHeight);
                i++;
            } 
            else if (point1.get(0) > point2.get(0)) {
                currentX = point2.get(0);
                currentRightHeight = point2.get(1);
                maxActiveHeight = Math.max(currentLeftHeight, currentRightHeight);
                j++;
            } 
            // If they land on the exact same X-coordinate, process both simultaneously
            else {
                currentX = point1.get(0);
                currentLeftHeight = point1.get(1);
                currentRightHeight = point2.get(1);
                maxActiveHeight = Math.max(currentLeftHeight, currentRightHeight);
                i++;
                j++;
            }

            // THE REDUNDANCY CHECK:
            // We ONLY add a new point to the final skyline if the height actually changed!
            if (merged.isEmpty() || merged.get(merged.size() - 1).get(1) != maxActiveHeight) {
                merged.add(Arrays.asList(currentX, maxActiveHeight));
            }
        }

        // Dump any remaining points from the left side
        while (i < left.size()) {
            merged.add(left.get(i++));
        }
        // Dump any remaining points from the right side
        while (j < right.size()) {
            merged.add(right.get(j++));
        }

        return merged;
    }
}