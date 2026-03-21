class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        // Suboptimal: Using a heavy Map to track both the visited word AND its current step count
        Map<String, Integer> visitedSteps = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(beginWord);
        visitedSteps.put(beginWord, 1);

        while (!queue.isEmpty()) {
            String currentWord = queue.poll();
            int currentStep = visitedSteps.get(currentWord);

            // We found the target!
            if (currentWord.equals(endWord)) {
                return currentStep;
            }

            // Iterate through every single character position of the current word
            for (int i = 0; i < currentWord.length(); i++) {
                
                // Try substituting every letter of the alphabet
                for (char c = 'a'; c <= 'z'; c++) {
                    if (currentWord.charAt(i) == c) {
                        continue;
                    }

                    // HEAVY LIFTING: Generating thousands of garbage strings using concatenation
                    // instead of a simple, memory-friendly char[] array swap!
                    String nextWord = currentWord.substring(0, i) + c + currentWord.substring(i + 1);

                    // If it's a valid dictionary word and we haven't processed it yet
                    if (dict.contains(nextWord) && !visitedSteps.containsKey(nextWord)) {
                        visitedSteps.put(nextWord, currentStep + 1);
                        queue.add(nextWord);
                    }
                }
            }
        }

        // The queue emptied out and we never reached the endWord
        return 0;
    }
}