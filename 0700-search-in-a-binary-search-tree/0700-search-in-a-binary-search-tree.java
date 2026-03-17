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
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        
        // Blindly search the entire left side, completely ignoring the BST rules
        TreeNode leftResult = searchBST(root.left, val);
        
        // If we found it on the left, bubble it up
        if (leftResult != null) {
            return leftResult;
        }
        
        // Otherwise, blindly search the entire right side
        return searchBST(root.right, val);
    }
}