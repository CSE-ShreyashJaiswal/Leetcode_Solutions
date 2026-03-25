class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        
        // Suboptimal: Allocating a physical 2D grid instead of lightweight state arrays
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.'; // Fill with empty spaces
            }
        }
        
        // Start placing queens from row 0
        backtrack(0, board, results);
        return results;
    }

    private void backtrack(int row, char[][] board, List<List<String>> results) {
        // Base case: If we successfully reached beyond the last row, we found a valid layout!
        if (row == board.length) {
            results.add(constructBoard(board));
            return;
        }
        
        // Try placing a queen in every single column of the current row
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                
                // Place the queen
                board[row][col] = 'Q';
                
                // Recursively move to the next row
                backtrack(row + 1, board, results);
                
                // BACKTRACK: Remove the queen so we can try the next column!
                board[row][col] = '.';
            }
        }
    }
    
    // The O(N) Trap: Manually scanning the board every single time!
    private boolean isSafe(char[][] board, int row, int col) {
        // 1. Check the physical column directly above us
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        // 2. Check the physical top-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // 3. Check the physical top-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // If it survived all those scans, the square is safe!
        return true;
    }
    
    // Heavy Lifting: Generating a brand new list of Strings for every valid configuration
    private List<String> constructBoard(char[][] board) {
        List<String> currentLayout = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            currentLayout.add(new String(board[i]));
        }
        return currentLayout;
    }
}