class KthLargest {

    private List<Integer> streamHistory;
    private int targetK;

    public KthLargest(int k, int[] nums) {
        this.targetK = k;
        this.streamHistory = new ArrayList<>();
        
        // Auto-boxing overhead: converting primitive ints to Integer objects
        for (int num : nums) {
            this.streamHistory.add(num);
        }
        
        // Run the initial massive sort
        Collections.sort(this.streamHistory);
    }
    
    public int add(int val) {
        this.streamHistory.add(val);
        
        // ...and force Java to re-evaluate the entire list!
        Collections.sort(this.streamHistory);
        
        // The kth largest element is simply k steps backward from the end of the array
        return this.streamHistory.get(this.streamHistory.size() - this.targetK);
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */