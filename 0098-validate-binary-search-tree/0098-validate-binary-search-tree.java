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
    public boolean isValidBST(TreeNode root) {
        List<Integer> extractedValues = new ArrayList<>();
        
        // Pass 1: Extract every single node value using Inorder Traversal (Left -> Root -> Right)
        inorderExtraction(root, extractedValues);
        
        // Pass 2: Scan the massive list to ensure it is strictly increasing
        for (int i = 1; i < extractedValues.size(); i++) {
            // A valid BST cannot have duplicate values or decreasing values!
            if (extractedValues.get(i) <= extractedValues.get(i - 1)) {
                return false;
            }
        }
        
        return true;
    }

    private void inorderExtraction(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        
        inorderExtraction(node.left, values);
        values.add(node.val);
        inorderExtraction(node.right, values);
    }
}