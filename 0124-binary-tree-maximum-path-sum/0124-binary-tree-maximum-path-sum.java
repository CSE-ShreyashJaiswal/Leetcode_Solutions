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
    public int maxPathSum(TreeNode root) {
        int[] globalMax = new int[] { Integer.MIN_VALUE };
        
        // Launch the single, highly-optimized recursive sweep
        calculateBranch(root, globalMax);
        
        return globalMax[0];
    }

    private int calculateBranch(TreeNode node, int[] globalMax) {
        if (node == null) {
            return 0;
        }

        // Recursively find the max branch going left and right. 
        // We use Math.max(0, ...) to completely ignore negative branches!
        int leftBranch = Math.max(0, calculateBranch(node.left, globalMax));
        int rightBranch = Math.max(0, calculateBranch(node.right, globalMax));

        // Calculate the full "U-shaped" path arching through THIS specific node
        int currentUPathSum = node.val + leftBranch + rightBranch;
        
        // Update our global tracker if this new U-path is the biggest we've seen
        globalMax[0] = Math.max(globalMax[0], currentUPathSum);

        // Return the best single straight-down branch for the parent node to use
        return node.val + Math.max(leftBranch, rightBranch);
    }
}