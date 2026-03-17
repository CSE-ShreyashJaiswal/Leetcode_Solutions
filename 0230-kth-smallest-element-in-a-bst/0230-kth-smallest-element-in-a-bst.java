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
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> extractedValues = new ArrayList<>();
        
        // Pass 1: Extract absolutely every single node value from smallest to largest
        inorderExtraction(root, extractedValues);
        
        // Pass 2: Just grab the specific index we want (subtract 1 because arrays are 0-indexed)
        return extractedValues.get(k - 1);
    }

    private void inorderExtraction(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        
        // Traverse left (smallest values)
        inorderExtraction(node.left, values);
        
        // Add the current root
        values.add(node.val);
        
        // Traverse right (larger values)
        inorderExtraction(node.right, values);
    }
}