package templates;

/**
 * Disjoint Set Union (DSU) or Union-Find Data Structure
 * Optimized with Path Compression and Union by Rank
 * 
 * Time Complexity: O(α(N)) ~ O(1) for both find and union operations
 * Space Complexity: O(N)
 */
public class DSU {
    private int[] parent;
    private int[] rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Every node is its own parent initially
            rank[i] = 1;   // Initial rank is 1
        }
    }

    // Find the root of the set in which element `i` belongs
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Path compression
        return parent[i] = find(parent[i]);
    }

    // Unite two sets
    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            // Union by rank
            if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }
        }
    }

    // Check if two elements are in the same set
    public boolean connected(int i, int j) {
        return find(i) == find(j);
    }
}
