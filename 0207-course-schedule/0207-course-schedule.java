class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<>());
        }
        
        // Build the graph: pre[1] must be taken before pre[0]
        for (int[] pre : prerequisites) {
            adjList.get(pre[1]).add(pre[0]);
        }
        
        // Clunky: Using a Set with Integer objects to cache our fully verified courses
        Set<Integer> clearedCourses = new HashSet<>();
        
        for (int i = 0; i < numCourses; i++) {
            // We create a brand new HashSet for the recursion stack on every new starting node!
            Set<Integer> currentPath = new HashSet<>();
            if (!dfs(i, adjList, currentPath, clearedCourses)) {
                return false; // A cycle was detected!
            }
        }
        
        return true;
    }

    private boolean dfs(int course, Map<Integer, List<Integer>> adjList, 
                        Set<Integer> currentPath, Set<Integer> clearedCourses) {
        
        // If we've already completely verified this course is cycle-free in a previous run, skip it
        if (clearedCourses.contains(course)) {
            return true;
        }
        
        // If we see the course in our CURRENT active path, we've walked in a circle!
        if (currentPath.contains(course)) {
            return false;
        }
        
        // Mark the course as part of the current exploration path
        currentPath.add(course);
        
        // Check all courses that depend on this one
        for (int nextCourse : adjList.get(course)) {
            if (!dfs(nextCourse, adjList, currentPath, clearedCourses)) {
                return false;
            }
        }
        
        // Backtrack: Remove from the current path so other branches can explore it normally
        currentPath.remove(course);
        
        // Mark as permanently cleared to avoid TLE on overlapping subtrees
        clearedCourses.add(course);
        
        return true;
    }
}