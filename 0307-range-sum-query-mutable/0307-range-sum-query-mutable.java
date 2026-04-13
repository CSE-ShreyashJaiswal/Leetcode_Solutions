class NumArray {
    // The bare-metal memory banks
    private int[] tree;
    private int[] nums;
    private int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        // A Fenwick Tree is strictly 1-indexed to make the bitwise math work
        this.tree = new int[n + 1]; 
        
        // Boot up the tree using the $O(N)$ linear build algorithm
        for (int i = 0; i < n; i++) {
            tree[i + 1] = nums[i];
        }
        
        for (int i = 1; i <= n; i++) {
            // Isolate the lowest set bit using Two's Complement
            int parent = i + (i & -i); 
            if (parent <= n) {
                tree[parent] += tree[i];
            }
        }
    }
    
    public void update(int index, int val) {
        // Calculate the exact difference (delta) to apply
        int delta = val - nums[index];
        // Synchronize our backing array
        nums[index] = val; 
        
        // Blast the delta up the Fenwick Tree using bitwise jumping
        // Convert the 0-indexed query to 1-indexed
        for (int i = index + 1; i <= n; i += (i & -i)) {
            tree[i] += delta;
        }
    }
    
    // Calculates the sum from index 0 to the target index
    private int prefixSum(int index) {
        int sum = 0;
        
        // Traverse back down the Fenwick Tree collecting the buckets
        for (int i = index + 1; i > 0; i -= (i & -i)) {
            sum += tree[i];
        }
        return sum;
    }
    
    public int sumRange(int left, int right) {
        // Mathematical range extraction: sum(0 to right) - sum(0 to left - 1)
        return prefixSum(right) - prefixSum(left - 1);
    }
}