/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        // Heavy Pass 1: Run a full BFS just to dump every single node into a massive List
        List<Node> allNodes = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(node);
        visited.add(node);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            allNodes.add(current);
            
            for (Node neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        
        // Heavy Pass 2: Iterate through the entire list and create isolated clones
        Map<Node, Node> cloneMap = new HashMap<>();
        for (Node originalNode : allNodes) {
            cloneMap.put(originalNode, new Node(originalNode.val));
        }
        
        // Heavy Pass 3: Iterate through the list AGAIN to manually stitch the edges together
        for (Node originalNode : allNodes) {
            Node clonedNode = cloneMap.get(originalNode);
            
            for (Node neighbor : originalNode.neighbors) {
                clonedNode.neighbors.add(cloneMap.get(neighbor));
            }
        }
        
        // Return the clone of the starting node
        return cloneMap.get(node);
    }
}