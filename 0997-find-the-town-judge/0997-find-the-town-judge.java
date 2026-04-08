class Solution {
    public int findJudge(int n, int[][] trust) {
        // Base case: A town of 1 person with no trust links means they are automatically the judge.
        if (n == 1 && trust.length == 0) {
            return 1;
        }

        // Suboptimal Heavy Lifting: Massive Maps of Sets instead of a sleek primitive int[] array!
        Map<Integer, Set<Integer>> whoTheyTrust = new HashMap<>();
        Map<Integer, Set<Integer>> whoTrustsThem = new HashMap<>();

        // THE CLUNKY TRAP: Forcing the JVM to allocate hundreds of HashSet objects!
        for (int[] t : trust) {
            int truster = t[0];
            int trusted = t[1];

            // Auto-boxing the primitive ints and allocating brand new Set objects in memory
            whoTheyTrust.putIfAbsent(truster, new HashSet<>());
            whoTheyTrust.get(truster).add(trusted);

            whoTrustsThem.putIfAbsent(trusted, new HashSet<>());
            whoTrustsThem.get(trusted).add(truster);
        }

        // Sweep through the town population looking for the specific graph signature
        for (int i = 1; i <= n; i++) {
            
            // Condition 1: The judge trusts NOBODY.
            // Our heavy map either doesn't have them, or their outgoing set is completely empty.
            boolean trustsNobody = !whoTheyTrust.containsKey(i) || whoTheyTrust.get(i).isEmpty();

            // Condition 2: EVERYBODY trusts the judge.
            // They must exist in the incoming map, and the size of their Set must be exactly N - 1.
            boolean trustedByEveryone = whoTrustsThem.containsKey(i) && whoTrustsThem.get(i).size() == n - 1;

            if (trustsNobody && trustedByEveryone) {
                return i;
            }
        }

        return -1; // No judge exists in this town
    }
}