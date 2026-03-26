class Solution {
    public int numberOfMatches(int n) {
        Queue<Integer> tournamentBracket = new LinkedList<>();
        
        // Auto-boxing overhead: converting primitive ints to Integer objects
        for (int i = 1; i <= n; i++) {
            tournamentBracket.add(i);
        }
        
        int totalMatches = 0;
        
        // Keep pulling teams to play until only the champion remains
        while (tournamentBracket.size() > 1) {
            // Pull two teams from the front of the line
            int teamA = tournamentBracket.poll();
            int teamB = tournamentBracket.poll();
            
            // A match is played!
            totalMatches++;
            
            // The winner (we arbitrarily choose teamA) advances to the next round
            // by going back into the queue
            tournamentBracket.add(teamA);
        }
        
        return totalMatches;
    }
}