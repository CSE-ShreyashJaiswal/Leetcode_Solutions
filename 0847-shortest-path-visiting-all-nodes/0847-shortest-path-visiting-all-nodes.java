class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        
        // Base case: If there's only 1 node, we're already done!
        if (n == 1) return 0;

        // Suboptimal Heavy Lifting: A generic Queue of massive Object Lists instead of primitive int[]
        Queue<List<Object>> queue = new LinkedList<>();
        
        // Heavy memory bank tracking our states using dynamically generated String objects
        Set<String> visitedBank = new HashSet<>();

        // We can start our journey from ANY node, so we must enqueue all of them as starting lines
        for (int i = 0; i < n; i++) {
            List<Object> initialState = new ArrayList<>();
            initialState.add(i); // Current Node (Auto-boxed to Integer)
            initialState.add(0); // Current Path Length (Auto-boxed to Integer)

            // Using a heavy TreeSet guarantees the elements are always sorted, 
            // ensuring our String keys are perfectly consistent!
            Set<Integer> initialVisited = new TreeSet<>();
            initialVisited.add(i);
            initialState.add(initialVisited);

            queue.add(initialState);

            // THE CLUNKY TRAP: Stringifying the entire TreeSet for our state check
            String stateKey = i + "-" + initialVisited.toString();
            visitedBank.add(stateKey);
        }

        while (!queue.isEmpty()) {
            List<Object> currentState = queue.poll();

            // Forcefully downcasting our heavy objects back to usable types
            int currNode = (Integer) currentState.get(0);
            int currLength = (Integer) currentState.get(1);
            
            @SuppressWarnings("unchecked")
            Set<Integer> visitedNodes = (TreeSet<Integer>) currentState.get(2);

            // If the size of our heavily maintained Set equals the graph size, we visited everywhere!
            if (visitedNodes.size() == n) {
                return currLength;
            }

            // Explore all connected neighbors
            for (int neighbor : graph[currNode]) {
                
                // Physically allocate and copy the entire heavy TreeSet for EVERY single neighbor check!
                Set<Integer> nextVisited = new TreeSet<>(visitedNodes);
                nextVisited.add(neighbor);

                // Generate a brand new String object to see if we've been in this exact state before.
                // The TreeSet automatically formats it beautifully like "2-[0, 1, 2]"
                String nextStateKey = neighbor + "-" + nextVisited.toString();

                // If this is a new state (or a new path to a known combination), explore it!
                if (!visitedBank.contains(nextStateKey)) {
                    visitedBank.add(nextStateKey);

                    // Allocate another massive generic list for the next queue entry
                    List<Object> nextState = new ArrayList<>();
                    nextState.add(neighbor);
                    nextState.add(currLength + 1);
                    nextState.add(nextVisited);

                    queue.add(nextState);
                }
            }
        }

        return -1;
    }
}