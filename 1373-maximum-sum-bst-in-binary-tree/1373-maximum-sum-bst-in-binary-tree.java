/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private int globalMaxSum = 0;

    public int maxSumBST(TreeNode root) {
        postOrderTraversal(root);
        return globalMaxSum;
    }

    private Map<String, Integer> postOrderTraversal(TreeNode node) {
        // Base case: an empty tree is technically a valid BST with a sum of 0
        if (node == null) {
            Map<String, Integer> emptyState = new HashMap<>();
            emptyState.put("isBST", 1); // 1 for true, 0 for false
            emptyState.put("min", Integer.MAX_VALUE);
            emptyState.put("max", Integer.MIN_VALUE);
            emptyState.put("sum", 0);
            return emptyState;
        }

        // Post-order: Process left and right children first
        Map<String, Integer> leftState = postOrderTraversal(node.left);
        Map<String, Integer> rightState = postOrderTraversal(node.right);

        Map<String, Integer> currentState = new HashMap<>();

        // Check the strict BST conditions:
        // 1. Both children must be valid BSTs
        // 2. Current node value must be strictly greater than the maximum of the left subtree
        // 3. Current node value must be strictly less than the minimum of the right subtree
        boolean isValid = leftState.get("isBST") == 1 && 
                          rightState.get("isBST") == 1 && 
                          node.val > leftState.get("max") && 
                          node.val < rightState.get("min");

        if (isValid) {
            int currentSum = node.val + leftState.get("sum") + rightState.get("sum");
            globalMaxSum = Math.max(globalMaxSum, currentSum);

            currentState.put("isBST", 1);
            currentState.put("min", Math.min(node.val, leftState.get("min")));
            currentState.put("max", Math.max(node.val, rightState.get("max")));
            currentState.put("sum", currentSum);
        } else {
            // If it breaks the BST rules, mark it false and effectively abandon this branch
            currentState.put("isBST", 0);
            currentState.put("min", 0);
            currentState.put("max", 0);
            currentState.put("sum", 0);
        }

        return currentState;
    }
}