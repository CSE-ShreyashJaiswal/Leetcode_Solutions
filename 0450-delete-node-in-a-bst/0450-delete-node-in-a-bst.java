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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        
        // We use a heavy list to store the ENTIRE tree's footprint, minus the target key
        List<Integer> extractedValues = new ArrayList<>();
        
        // Pass 1: Extract absolutely every node EXCEPT the one we want to delete
        inorderExtraction(root, key, extractedValues);
        
        // Pass 2: Rebuild a brand new tree from scratch!
        return buildTree(extractedValues, 0, extractedValues.size() - 1);
    }

    private void inorderExtraction(TreeNode node, int key, List<Integer> values) {
        if (node == null) {
            return;
        }
        
        inorderExtraction(node.left, key, values);
        
        // Only add the value if it IS NOT the key we are trying to delete
        if (node.val != key) {
            values.add(node.val);
        }
        
        inorderExtraction(node.right, key, values);
    }
    
    // Helper 2: The classic "Convert Sorted Array to BST" heavy lifter
    private TreeNode buildTree(List<Integer> values, int left, int right) {
        if (left > right) {
            return null;
        }
        
        // Find the middle to keep the new tree perfectly balanced
        int mid = left + (right - left) / 2;
        TreeNode newRoot = new TreeNode(values.get(mid));
        
        // Recursively allocate completely new nodes for the children
        newRoot.left = buildTree(values, left, mid - 1);
        newRoot.right = buildTree(values, mid + 1, right);
        
        return newRoot;
    }
}