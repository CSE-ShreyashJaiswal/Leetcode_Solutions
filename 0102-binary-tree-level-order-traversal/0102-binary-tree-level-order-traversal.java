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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        // Pass 1: Find the absolute bottom of the tree
        int height = getHeight(root);
        
        // Pass 2 to Height: Start from the root AGAIN for every single level!
        for (int i = 1; i <= height; i++) {
            List<Integer> currentLevel = new ArrayList<>();
            fetchNodesAtLevel(root, i, currentLevel);
            result.add(currentLevel);
        }
        
        return result;
    }
    
    // Helper 1: Standard DFS to find maximum depth
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    
    // Helper 2: Walk down from the root just to grab nodes at 'targetLevel'
    private void fetchNodesAtLevel(TreeNode root, int targetLevel, List<Integer> levelList) {
        if (root == null) {
            return;
        }
        if (targetLevel == 1) {
            // We reached our target depth for this specific loop iteration!
            levelList.add(root.val);
        } else if (targetLevel > 1) {
            // Keep walking down, decrementing the target as we go
            fetchNodesAtLevel(root.left, targetLevel - 1, levelList);
            fetchNodesAtLevel(root.right, targetLevel - 1, levelList);
        }
    }
}