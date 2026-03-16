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
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int depth = 0;
        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        
        // Process the tree one horizontal slice at a time
        while (!currentLevel.isEmpty()) {
            depth++; // We are stepping down into a new level
            
            // Create a brand new list for the next horizontal slice
            List<TreeNode> nextLevel = new ArrayList<>();
            
            for (TreeNode node : currentLevel) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            
            // Discard the old level and move down to the new one
            currentLevel = nextLevel;
        }
        
        return depth;
    }
}