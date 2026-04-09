class Solution {
    // A global stopwatch to track exactly when we hit each node
    private int timer = 1;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Step 1: Build the bare-metal Adjacency List using an Array of Lists
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Populate the graph 
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }

        List<List<Integer>> bridges = new ArrayList<>();
        
        // Memory banks for Tarjan's logic
        int[] discoveryTime = new int[n];
        int[] lowestReachable = new int[n];

        // The problem guarantees a fully connected graph, so one DFS from node 0 covers everything
        dfs(0, -1, graph, discoveryTime, lowestReachable, bridges);

        return bridges;
    }

    private void dfs(int current, int parent, List<Integer>[] graph, int[] disc, int[] low, List<List<Integer>> bridges) {
        // Stamp the current node with the stopwatch time
        disc[current] = timer;
        low[current] = timer;
        timer++;

        for (int neighbor : graph[current]) {
            // Instantly ignore the edge we literally just walked down
            if (neighbor == parent) {
                continue;
            }

            // If discoveryTime is 0, we have never visited this neighbor
            if (disc[neighbor] == 0) {
                // Dive deeper into the graph
                dfs(neighbor, current, graph, disc, low, bridges);

                // As we come back up from the recursion, sync the lowest reachable times
                low[current] = Math.min(low[current], low[neighbor]);

                // THE CRITICAL CHECK: 
                // Did the neighbor find a back-edge to safety? 
                // If its lowest reachable time is strictly greater than our current timestamp,
                // it is mathematically trapped. This edge is a bridge!
                if (low[neighbor] > disc[current]) {
                    bridges.add(Arrays.asList(current, neighbor));
                }
                
            } else {
                // We hit a back-edge! A shortcut to an already visited node.
                // Update our lowest reachable time to that neighbor's original discovery time.
                low[current] = Math.min(low[current], disc[neighbor]);
            }
        }
    }
}