class Solution {
    public int[][] kClosest(int[][] points, int k) {
        List<int[]> pointList = new ArrayList<>();
        for (int[] p : points) {
            pointList.add(p);
        }
        
        // Heavy Lifting: Sort the ENTIRE list, comparing the squared distances on the fly!
        // We don't need to take the square root (Math.sqrt) because if A^2 > B^2, then A > B.
        Collections.sort(pointList, (a, b) -> {
            int distanceA = (a[0] * a[0]) + (a[1] * a[1]);
            int distanceB = (b[0] * b[0]) + (b[1] * b[1]);
            
            // Sort in ascending order (smallest distance first)
            return Integer.compare(distanceA, distanceB);
        });
        
        // Pluck out the first 'k' elements to ship back
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = pointList.get(i);
        }
        
        return result;
    }
}