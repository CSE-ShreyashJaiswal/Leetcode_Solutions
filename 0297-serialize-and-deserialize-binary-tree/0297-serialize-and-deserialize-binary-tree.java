/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        // Pass 1: Build the massive comma-separated string horizontally
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        
        // This is a heavy operation! Creating a full array of strings in memory.
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        // Start from index 1 since the root is already at index 0
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = queue.poll();
            
            // Look at the next value in the array for the left child
            if (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                queue.add(left);
            }
            // Move the pointer forward for the right child
            i++;
            
            // Look at the next value in the array for the right child
            if (i < values.length && !values[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                queue.add(right);
            }
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));