/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        
        // Pass 1: Manually record the exact path to node p
        findPath(root, p, pathP);
        
        // Pass 2: Manually record the exact path to node q
        findPath(root, q, pathQ);
        
        TreeNode lca = null;
        int i = 0;
        
        // Pass 3: Walk both paths simultaneously until they branch away from each other
        while (i < pathP.size() && i < pathQ.size()) {
            if (pathP.get(i) == pathQ.get(i)) {
                lca = pathP.get(i); // Keep updating the LCA as long as the paths match
            } else {
                break; // The paths have diverged!
            }
            i++;
        }
        
        return lca;
    }
    
    // Helper method to find the path from the root to a target node
    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        
        // Add the current node to our path tracker
        path.add(root);
        
        // We found it!
        if (root == target) {
            return true;
        }
        
        // Recursively search the left and right subtrees
        if (findPath(root.left, target, path) || findPath(root.right, target, path)) {
            return true;
        }
        
        // Backtrack: If the target isn't down this branch, remove this node from our path
        path.remove(path.size() - 1);
        return false;
    }
}