class Solution {

    private Map<Integer, Integer> memoBank = new HashMap<>();

    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        // The Clunky Trap: Forcing an Auto-Boxing Map lookup on every single recursive step!
        if (memoBank.containsKey(n)) {
            return memoBank.get(n);
        }

        // Recursively calculate the branches for taking 1 step and taking 2 steps
        int totalWays = climbStairs(n - 1) + climbStairs(n - 2);

        // Store the result as heavy Integer objects in our map to prevent Time Limit Exceeded
        memoBank.put(n, totalWays);

        return totalWays;
    }
}