class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Load all the unique numbers into a standard List
        List<Integer> uniqueNums = new ArrayList<>(frequencyMap.keySet());

        // Step 3: Sort the entire list in descending order based on their frequency counts
        Collections.sort(uniqueNums, (a, b) -> frequencyMap.get(b) - frequencyMap.get(a));

        // Step 4: Grab the first k elements for the final answer
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = uniqueNums.get(i);
        }

        return result;
    }
}