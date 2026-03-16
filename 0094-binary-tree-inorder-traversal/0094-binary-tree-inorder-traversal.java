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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        // Fetch the newly created left list and copy every single element into ours
        result.addAll(inorderTraversal(root.left));
        
        // Process the current node (Inorder: Left -> Root -> Right)
        result.add(root.val);
        
        // Fetch the newly created right list and copy every single element into ours
        result.addAll(inorderTraversal(root.right));
        
        return result;
    }
}