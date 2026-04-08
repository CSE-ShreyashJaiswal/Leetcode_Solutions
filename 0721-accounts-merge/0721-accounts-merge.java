class Solution {
    // Our bare-metal DSU engine
    private int[] parent;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // LeetCode constraints: 1000 accounts * 10 emails = max 10,000 unique emails
        int maxEmails = 10001; 
        parent = new int[maxEmails];
        for (int i = 0; i < maxEmails; i++) {
            parent[i] = i; 
        }
        
        Map<String, Integer> emailToId = new HashMap<>();
        Map<Integer, String> idToName = new HashMap<>();
        int idGenerator = 0;
        
        // Step 1: Compress Strings into primitive Integers and build the DSU
        for (List<String> account : accounts) {
            String name = account.get(0);
            int firstEmailId = -1; // Acts as the anchor for this specific account
            
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                
                // If we haven't seen this email, assign it a brand new hardware ID
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, idGenerator);
                    idToName.put(idGenerator, name);
                    idGenerator++;
                }
                
                int currentEmailId = emailToId.get(email);
                
                if (firstEmailId == -1) {
                    firstEmailId = currentEmailId; // Set the anchor
                } else {
                    // Union the current email with the anchor email
                    union(firstEmailId, currentEmailId);
                }
            }
        }
        
        // Step 2: Group all identical emails under their absolute root Boss ID
        Map<Integer, List<String>> rootToEmails = new HashMap<>();
        
        for (String email : emailToId.keySet()) {
            int emailId = emailToId.get(email);
            int absoluteRoot = find(emailId); // O(1) Path Compression lookup
            
            rootToEmails.putIfAbsent(absoluteRoot, new ArrayList<>());
            rootToEmails.get(absoluteRoot).add(email);
        }
        
        // Step 3: Format the final output strings as requested
        List<List<String>> mergedAccounts = new ArrayList<>();
        
        for (int root : rootToEmails.keySet()) {
            List<String> emails = rootToEmails.get(root);
            
            // The problem strictly requires the emails to be sorted alphabetically
            Collections.sort(emails); 
            
            // Reattach the name to the front of the list
            emails.add(0, idToName.get(root)); 
            mergedAccounts.add(emails);
        }
        
        return mergedAccounts;
    }

    // Path compression keeps the tree completely flat
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    // Instantly merge two sets via their root bosses
    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }
}