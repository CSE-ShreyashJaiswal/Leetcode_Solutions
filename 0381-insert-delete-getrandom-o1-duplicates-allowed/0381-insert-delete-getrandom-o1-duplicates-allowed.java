class RandomizedCollection {
    // The contiguous memory block for O(1) random hardware jumps
    private List<Integer> nums;
    
    // The Memory Router: Maps a value to a set of its exact physical indices
    private Map<Integer, LinkedHashSet<Integer>> indices;
    
    private Random rand;

    public RandomizedCollection() {
        nums = new ArrayList<>();
        indices = new HashMap<>();
        rand = new Random();
    }
    
    public boolean insert(int val) {
        boolean isFirstAddition = !indices.containsKey(val) || indices.get(val).isEmpty();
        
        // Register the new physical index in the memory router
        indices.computeIfAbsent(val, k -> new LinkedHashSet<>()).add(nums.size());
        
        // Append the value to our contiguous memory block
        nums.add(val);
        
        return isFirstAddition;
    }
    
    public boolean remove(int val) {
        // If the value literally does not exist, abort the protocol
        if (!indices.containsKey(val) || indices.get(val).isEmpty()) {
            return false; 
        }
        
        // Grab the specific set of physical indices for this value
        LinkedHashSet<Integer> valIndices = indices.get(val);
        
        // THE O(1) RETRIEVAL: Pluck the absolute first available index
        int indexToRemove = valIndices.iterator().next();
        
        // Immediately sever this specific index from the router
        valIndices.remove(indexToRemove);
        
        int lastIndex = nums.size() - 1;
        int lastElement = nums.get(lastIndex);
        
        // THE O(1) SWAP-AND-POP:
        // If the element we are destroying is NOT already sitting at the absolute tail...
        if (indexToRemove != lastIndex) {
            // Physically plug the hole using the last element
            nums.set(indexToRemove, lastElement);
            
            // Update the router to reflect the last element's new physical location
            indices.get(lastElement).remove(lastIndex);
            indices.get(lastElement).add(indexToRemove);
        }
        
        // Sever the physical tail of the array in pure O(1) time
        nums.remove(lastIndex);
        
        return true;
    }
    
    public int getRandom() {
        // Hardware-level random jump directly into the contiguous L1 cache footprint
        return nums.get(rand.nextInt(nums.size()));
    }
}