class Solution {
        private Map<String, Integer> memoBank = new HashMap<>();

        public int calculateMinimumHP(int[][] dungeon) {
        return calculate(dungeon, 0, 0);
    }
    
    private int calculate(int[][] dungeon, int row, int col) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // Base case: If we step out of bounds, it requires "infinite" health to survive
        if (row >= m || col >= n) {
            return Integer.MAX_VALUE;
        }

        // THE CLUNKY TRAP: Generating a brand new String object for EVERY single coordinate check!
        String posKey = row + "," + col;

        if (memoBank.containsKey(posKey)) {
            return memoBank.get(posKey);
        }

        // Base case: We reached the princess in the bottom-right corner!
        if (row == m - 1 && col == n - 1) {
            // If the room has a demon (negative), we need at least that much health + 1 to survive.
            // If it has a health potion (positive), we only need the bare minimum 1 health.
            int requiredHealth = 1 - dungeon[row][col];
            return requiredHealth <= 0 ? 1 : requiredHealth;
        }

        // Recursively check the paths going strictly RIGHT and strictly DOWN
        int healthFromRight = calculate(dungeon, row, col + 1);
        int healthFromDown = calculate(dungeon, row + 1, col);

        // We want to take the path that requires the LEAST amount of starting health
        int minHealthRequiredNext = Math.min(healthFromRight, healthFromDown);

        // Calculate how much health we need BEFORE entering the current room
        int healthNeededHere = minHealthRequiredNext - dungeon[row][col];

        // We can never drop below 1 health, even if the current room is packed with potions
        int finalHealth = healthNeededHere <= 0 ? 1 : healthNeededHere;

        // Store the heavy Integer object in our map using our dynamically generated String key
        memoBank.put(posKey, finalHealth);

        return finalHealth;
    }
}