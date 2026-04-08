class Solution {
    public int findCenter(int[][] edges) {
        // Extract the two nodes from the very first edge
        int firstNode = edges[0][0];
        int secondNode = edges[0][1];
        
        // Extract the two nodes from the second edge
        int thirdNode = edges[1][0];
        int fourthNode = edges[1][1];
        
        // If the first node of edge 0 appears anywhere in edge 1, it's our center
        if (firstNode == thirdNode || firstNode == fourthNode) {
            return firstNode;
        }
        
        // Otherwise, it mathematically MUST be the second node
        return secondNode;
    }
}