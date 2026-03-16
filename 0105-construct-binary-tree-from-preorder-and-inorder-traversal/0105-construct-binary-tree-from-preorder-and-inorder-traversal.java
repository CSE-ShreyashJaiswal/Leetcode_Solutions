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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        // The very first element in a preorder traversal is ALWAYS the current root!
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);

        // Scan the inorder array to find where our root is hiding
        int rootIndexInorder = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                rootIndexInorder = i;
                break;
            }
        }

        // Suboptimal Heavy Lifting: Physically slice and copy the arrays for the children!
        // Everything to the left of the root in the inorder array belongs to the left subtree
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, rootIndexInorder);
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, rootIndexInorder + 1);

        // Everything to the right of the root in the inorder array belongs to the right subtree
        int[] rightInorder = Arrays.copyOfRange(inorder, rootIndexInorder + 1, inorder.length);
        int[] rightPreorder = Arrays.copyOfRange(preorder, rootIndexInorder + 1, preorder.length);

        // Recursively build out the tree using our brand new, heavy array copies
        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightInorder);

        return root;
    }
}