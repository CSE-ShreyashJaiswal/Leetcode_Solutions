import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    // The DSU memory banks
    private Map<String, String> parent = new HashMap<>();
    private Map<String, Double> weight = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        // Step 1: Boot up the DSU and process all equations
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            
            // Initialize standalone variables if we haven't seen them
            parent.putIfAbsent(u, u);
            parent.putIfAbsent(v, v);
            weight.putIfAbsent(u, 1.0);
            weight.putIfAbsent(v, 1.0);
            
            // Merge them using the mathematical exchange rate
            union(u, v, values[i]);
        }
        
        // Step 2: Evaluate the queries instantaneously
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String u = queries.get(i).get(0);
            String v = queries.get(i).get(1);
            
            // If the variables literally do not exist in our system
            if (!parent.containsKey(u) || !parent.containsKey(v)) {
                result[i] = -1.0;
            } else {
                String rootU = find(u);
                String rootV = find(v);
                
                // If they report to different bosses, no path exists
                if (!rootU.equals(rootV)) {
                    result[i] = -1.0;
                } else {
                    // They share a boss! The division is just their relative weights
                    result[i] = weight.get(u) / weight.get(v);
                }
            }
        }
        
        return result;
    }
    
    // Path Compression: Flattens the hierarchy and compounds the multipliers
    private String find(String i) {
        if (parent.get(i).equals(i)) {
            return i;
        }
        
        String currentParent = parent.get(i);
        String absoluteRoot = find(currentParent); // Recursively find the top boss
        
        // Flatten the tree: Point directly to the top boss
        parent.put(i, absoluteRoot);
        // Compound the exchange rate: (my weight relative to parent) * (parent's weight relative to boss)
        weight.put(i, weight.get(i) * weight.get(currentParent));
        
        return absoluteRoot;
    }
    
    // Weighted Merge Logic
    private void union(String u, String v, double value) {
        String rootU = find(u);
        String rootV = find(v);
        
        if (!rootU.equals(rootV)) {
            // We want to link rootU to rootV.
            // We know: u = weight[u] * rootU
            // We know: v = weight[v] * rootV
            // Equation given: u = value * v
            // Substitute: weight[u] * rootU = value * (weight[v] * rootV)
            // Solve for rootU: rootU = (value * weight[v] / weight[u]) * rootV
            
            parent.put(rootU, rootV);
            weight.put(rootU, (value * weight.get(v)) / weight.get(u));
        }
    }
}