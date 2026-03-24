class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];
        
        // Suboptimal Heavy Lifting: Dump all primitive ints into a heavy ArrayList
        List<Integer> sortedScores = new ArrayList<>();
        for (int s : score) {
            sortedScores.add(s); // Auto-boxing overhead happening here!
        }
        
        // Sort the list in descending order so the highest scores are at the front
        Collections.sort(sortedScores, Collections.reverseOrder());
        
        // Iterate through the original array to assign ranks
        for (int i = 0; i < n; i++) {
            
            // This is the O(N^2) trap! 
            // .indexOf() forces a linear search from index 0 every single time!
            int rankIndex = sortedScores.indexOf(score[i]); 
            
            // Assign the correct string based on their placement
            if (rankIndex == 0) {
                result[i] = "Gold Medal";
            } else if (rankIndex == 1) {
                result[i] = "Silver Medal";
            } else if (rankIndex == 2) {
                result[i] = "Bronze Medal";
            } else {
                // Arrays are 0-indexed, so we add 1 for the actual rank number
                result[i] = String.valueOf(rankIndex + 1);
            }
        }
        
        return result;
    }
}