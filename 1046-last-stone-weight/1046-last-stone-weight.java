class Solution {
    public int lastStoneWeight(int[] stones) {
        List<Integer> stoneList = new ArrayList<>();
        
        // Auto-boxing overhead: converting primitive ints to Integer objects
        for (int stone : stones) {
            stoneList.add(stone);
        }

        // Keep smashing until we have 1 or 0 stones left
        while (stoneList.size() > 1) {
            
            // Suboptimal Heavy Lifting: Completely re-sorting the entire list every single time!
            Collections.sort(stoneList);
            
            // Grab the two heaviest stones (which are now sitting at the very end)
            int heavy1 = stoneList.remove(stoneList.size() - 1);
            int heavy2 = stoneList.remove(stoneList.size() - 1);
            
            // If they aren't completely destroyed, add the remaining piece back into the mix
            if (heavy1 != heavy2) {
                stoneList.add(heavy1 - heavy2);
            }
        }

        // If the list is empty, return 0. Otherwise, return the lone surviving stone!
        return stoneList.isEmpty() ? 0 : stoneList.get(0);
    }
}