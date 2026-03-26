class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        // THE CLUNKY TRAP: Scanning the ENTIRE board from the very beginning (0, 0)
        // every single time this recursive function is called!
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                // We found an empty cell!
                if (board[i][j] == '.') {
                    
                    // Try placing digits '1' through '9'
                    for (char c = '1'; c <= '9'; c++) {
                        
                        // Run a heavy physical scan to ensure the placement is valid
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // Place the digit
                            
                            // Recursively try to solve the rest of the board
                            if (solve(board)) {
                                return true; // We found the final solution!
                            }
                            
                            // BACKTRACK: If it didn't lead to a solution, erase it and try the next number
                            board[i][j] = '.';
                        }
                    }
                    // If we tried 1-9 and none worked, this specific branch is a dead end
                    return false; 
                }
            }
        }
        // If the nested loops finish without finding a single '.', the board is full!
        return true;
    }

    // Heavy Manual Scanning for every single digit attempt
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            
            // Check the entire physical row
            if (board[row][i] == c) {
                return false;
            }
            
            // Check the entire physical column
            if (board[i][col] == c) {
                return false;
            }
            
            // Math trick to check the 3x3 physical sub-box
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) {
                return false;
            }
        }
        
        // If it survived all 3 loops, the square is safe!
        return true;
    }
}