class Solution {
    public int findKthLargest(int[] nums, int k) {
       PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Auto-boxing overhead: constantly converting primitive ints to Integer objects
        for (int num : nums) {
            maxHeap.add(num);
        }
        
        // Throw away the largest elements k - 1 times
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        
        // The element sitting at the top is exactly the kth largest!
        return maxHeap.poll(); 
    }
}