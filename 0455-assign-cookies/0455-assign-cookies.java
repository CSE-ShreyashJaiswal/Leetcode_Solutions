class Solution {
    public int findContentChildren(int[] g, int[] s) {
        PriorityQueue<Integer> children = new PriorityQueue<>();
        PriorityQueue<Integer> cookies = new PriorityQueue<>();
        
        // Auto-boxing overhead: converting primitive ints to Integer objects
        for (int greed : g) {
            children.add(greed);
        }
        for (int size : s) {
            cookies.add(size);
        }
        
        int contentChildren = 0;
        
        // Keep checking as long as we have both children waiting and cookies left
        while (!children.isEmpty() && !cookies.isEmpty()) {
            int currentChildGreed = children.peek();
            
            // Always pull the absolute smallest cookie available
            int currentCookieSize = cookies.poll(); 
            
            // If this cookie is big enough to satisfy the least greedy child
            if (currentCookieSize >= currentChildGreed) {
                contentChildren++;
                children.poll(); // The child is happy and leaves the line!
            }
            // If the cookie was too small, the if-statement skips, the cookie is 
            // naturally discarded, and the child stays at the front of the line.
        }
        
        return contentChildren;
    }
}