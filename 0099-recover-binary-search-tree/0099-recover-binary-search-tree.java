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
    public void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        
        // Pass 1: Extract every node using Inorder Traversal
        inorderExtraction(root, nodeList);
        
        TreeNode firstWrongNode = null;
        TreeNode secondWrongNode = null;
        
        // Pass 2: Scan the list to find the two nodes that break the sorted order
        for (int i = 0; i < nodeList.size() - 1; i++) {
            if (nodeList.get(i).val > nodeList.get(i + 1).val) {
                // The very first time we see a drop, the FIRST node in the pair is definitely wrong
                if (firstWrongNode == null) {
                    firstWrongNode = nodeList.get(i);
                }
                
                // We ALWAYS update the second wrong node when we see a drop.
                // If the swapped nodes are adjacent, this catches it in one go.
                // If they are far apart, a second drop later in the list will correctly overwrite this!
                secondWrongNode = nodeList.get(i + 1);
            }
        }
        
        // Pass 3: Physically swap their values to fix the tree
        if (firstWrongNode != null && secondWrongNode != null) {
            int temp = firstWrongNode.val;
            firstWrongNode.val = secondWrongNode.val;
            secondWrongNode.val = temp;
        }
    }

    private void inorderExtraction(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        
        inorderExtraction(node.left, list);
        
        // Store the actual node reference, not just the integer value!
        list.add(node);
        
        inorderExtraction(node.right, list);
    }
}