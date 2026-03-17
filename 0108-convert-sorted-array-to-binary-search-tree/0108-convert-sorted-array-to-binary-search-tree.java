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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        // Find the exact middle index to ensure the tree stays perfectly height-balanced
        int mid = nums.length / 2;
        
        // The middle element becomes the root of this current subtree
        TreeNode root = new TreeNode(nums[mid]);
        
        // Suboptimal Heavy Lifting: Physically slice the arrays!
        // Everything to the left of the middle belongs to the left subtree
        int[] leftHalf = Arrays.copyOfRange(nums, 0, mid);
        
        // Everything to the right of the middle belongs to the right subtree
        int[] rightHalf = Arrays.copyOfRange(nums, mid + 1, nums.length);
        
        // Recursively build out the children using our brand new, heavy array copies
        root.left = sortedArrayToBST(leftHalf);
        root.right = sortedArrayToBST(rightHalf);
        
        return root;
    }
}