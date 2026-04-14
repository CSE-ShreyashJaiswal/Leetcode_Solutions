class RangeModule {
    // The Sparse Memory Bank: Maps a 'start' coordinate to its 'end' coordinate
    private TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        // Step 1: Binary search for the nearest existing intervals
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        
        // Step 2: If our new interval falls inside or touches the boundary of an 
        // existing interval on the left, we stretch our mathematical boundary backward.
        if (start != null && map.get(start) >= left) {
            left = start;
        }
        
        // Step 3: Do the exact same boundary check on the right edge.
        if (end != null && map.get(end) > right) {
            right = map.get(end);
        }
        
        // THE HARDWARE SEVER: Instantly purge all smaller intervals trapped inside 
        // our new massive boundary by severing the Red-Black tree links.
        map.subMap(left, right).clear();
        
        // Drop the single, fused, contiguous interval into the map
        map.put(left, right);
    }
    
    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        // Mathematically, if an interval covers our query, its starting coordinate 
        // MUST be <= left, and its ending coordinate MUST stretch past our right edge.
        return start != null && map.get(start) >= right;
    }
    
    public void removeRange(int left, int right) {
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        
        // Lock in the old right-side boundaries before the purge
        int oldStartEnd = start != null ? map.get(start) : -1;
        int oldEndEnd = end != null ? map.get(end) : -1;
        
        // THE HARDWARE SEVER: Purge everything completely swallowed by the removal zone
        map.subMap(left, right).clear();
        
        // Step 1: Seal the Left Fracture
        // If our removal chopped the right tail off an existing interval, we overwrite 
        // the remaining left chunk back into memory.
        if (start != null && start < left && oldStartEnd > left) {
            map.put(start, left);
        }
        
        // Step 2: Seal the Right Fracture
        // If our removal chopped the left head off an existing interval, we re-insert 
        // the remaining right chunk.
        if (end != null && oldEndEnd > right) {
            map.put(right, oldEndEnd);
        }
    }
}
