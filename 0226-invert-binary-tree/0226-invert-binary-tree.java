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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        // Using an ArrayList as a Queue is a classic "second-best" move!
        List<TreeNode> clunkyQueue = new ArrayList<>();
        clunkyQueue.add(root);
        
        while (!clunkyQueue.isEmpty()) {
            // Removing from index 0 forces the entire array to shift left!
            TreeNode current = clunkyQueue.remove(0);
            
            // Swap the left and right children
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            
            // Add the newly swapped children to the back of our "queue"
            if (current.left != null) {
                clunkyQueue.add(current.left);
            }
            if (current.right != null) {
                clunkyQueue.add(current.right);
            }
        }
        
        return root;
    }
}