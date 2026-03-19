class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }

        // We use a heavy Map instead of a sleek primitive array to build our Adjacency List
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Build out the bi-directional roads
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // We use a heavy Set instead of a primitive boolean[] array to track where we've been
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // Start the BFS journey at the source
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Check all connected neighbors
            for (int neighbor : graph.get(current)) {
                if (neighbor == destination) {
                    return true; // We found the target!
                }
                
                // If we haven't visited this city yet, add it to our queue and mark it
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // The queue emptied out and we never hit the destination
        return false;
    }
}