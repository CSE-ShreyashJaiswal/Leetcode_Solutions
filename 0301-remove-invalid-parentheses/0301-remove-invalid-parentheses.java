class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0;
        int rightRem = 0;

        // Step 1: The Reconnaissance Sweep
        // Calculate the exact mathematical quota of misplaced parentheses
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--; // A valid pair mathematically cancels out
                } else {
                    rightRem++; // A strictly misplaced right parenthesis
                }
            }
        }

        // A HashSet automatically neutralizes duplicate valid expressions
        Set<String> validExpressions = new HashSet<>();
        
        // Boot up the DFS engine using a single, mutable hardware buffer
        dfs(s, 0, leftRem, rightRem, 0, 0, new StringBuilder(), validExpressions);
        
        return new ArrayList<>(validExpressions);
    }

    private void dfs(String s, int index, int leftRem, int rightRem, 
                     int openCount, int closeCount, StringBuilder sb, Set<String> result) {
        
        // Base Case: We have reached the absolute end of the string
        if (index == s.length()) {
            // If our removal quotas are zero, and the remaining brackets are perfectly balanced
            if (leftRem == 0 && rightRem == 0 && openCount == closeCount) {
                // Lock the valid state into our results
                result.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(index);
        
        // THE STATE CHECKPOINT: Mark the exact memory boundary of our buffer
        int currentLength = sb.length();

        // Scenario 1: The character is a standard letter. 
        // We MUST keep it. Append, dive, and revert.
        if (c != '(' && c != ')') {
            sb.append(c);
            dfs(s, index + 1, leftRem, rightRem, openCount, closeCount, sb, result);
            sb.setLength(currentLength); // O(1) Hardware Revert
            return;
        }

        // Scenario 2: DELETE the parenthesis (Branch 1)
        // We only attempt deletion if we still have quota left for that specific bracket!
        if (c == '(' && leftRem > 0) {
            dfs(s, index + 1, leftRem - 1, rightRem, openCount, closeCount, sb, result);
        } else if (c == ')' && rightRem > 0) {
            dfs(s, index + 1, leftRem, rightRem - 1, openCount, closeCount, sb, result);
        }

        // Scenario 3: KEEP the parenthesis (Branch 2)
        sb.append(c);
        if (c == '(') {
            dfs(s, index + 1, leftRem, rightRem, openCount + 1, closeCount, sb, result);
        } else if (c == ')') {
            // We can ONLY mathematically keep a closing bracket if there is an unmatched open bracket waiting for it!
            if (openCount > closeCount) {
                dfs(s, index + 1, leftRem, rightRem, openCount, closeCount + 1, sb, result);
            }
        }
        
        // THE O(1) HARDWARE REVERT: 
        // Snap the pointer back, instantly erasing the appended character without allocating new memory
        sb.setLength(currentLength);
    }
}