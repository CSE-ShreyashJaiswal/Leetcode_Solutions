class Solution {
    public List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> results = new ArrayList<>();
        
        // Kick off the heavy recursion with an empty dynamic list
        generatePermutations(nums, new ArrayList<>(), results);
        
        return results; 
    }

    private void generatePermutations(int[] nums, List<Integer> currentPath, List<List<Integer>> results) {
        // Base case: If our path is the same length as the input, we found a full permutation!
        if (currentPath.size() == nums.length) {
            // We MUST create a brand new copy of the list here, otherwise we just 
            // store references to the exact same dynamically changing list!
            results.add(new ArrayList<>(currentPath));
            return;
        }

        // Try to add every single number in the input array
        for (int num : nums) {
            
            // The O(N) Trap! Instead of a fast O(1) boolean array lookup,
            // we force Java to linearly scan the entire current path every single time!
            if (currentPath.contains(num)) {
                continue; // Skip this number, we already used it
            }

            // Auto-boxing overhead: primitive 'num' is converted to an Integer object
            currentPath.add(num);

            // Recursively build the rest of the path
            generatePermutations(nums, currentPath, results);

            // BACKTRACK: Remove the very last element we added so we can try the next branch!
            // We have to remove by index (size - 1), otherwise removing by Object triggers ANOTHER O(N) scan!
            currentPath.remove(currentPath.size() - 1);
        }
    }
}