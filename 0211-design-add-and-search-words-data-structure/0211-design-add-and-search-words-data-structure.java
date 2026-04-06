class WordDictionary {
    
    // A sleek, lightweight node containing exactly 26 primitive pointers
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode current = root;
        
        // Traverse the tree, forging new paths only when necessary
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Instant O(1) mathematical index calculation
            
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        
        // Mark the exact node where a complete word finishes
        current.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        // Kick off the recursive search starting from the very top
        return searchTree(word, 0, root);
    }
    
    private boolean searchTree(String word, int charIndex, TrieNode node) {
        // Base case: If we fell off the tree, this path is a dead end
        if (node == null) {
            return false;
        }
        
        // Base case: We successfully reached the end of the target string
        if (charIndex == word.length()) {
            return node.isEndOfWord; // Must actually be a complete word, not just a prefix!
        }
        
        char c = word.charAt(charIndex);
        
        if (c == '.') {
            // The Wildcard: We must recursively check ALL existing children at this level
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    // If ANY of the valid children lead to a successful match, bubble it up!
                    if (searchTree(word, charIndex + 1, node.children[i])) {
                        return true;
                    }
                }
            }
            return false; // None of the children led to a valid match
            
        } else {
            // Standard Character: Just jump directly to the mathematical index
            int index = c - 'a';
            return searchTree(word, charIndex + 1, node.children[index]);
        }
    }
}