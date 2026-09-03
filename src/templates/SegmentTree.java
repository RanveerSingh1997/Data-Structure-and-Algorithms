package templates;

/**
 * Segment Tree
 * Used for storing intervals/segments and allowing fast querying (e.g. Range Sum, Range Min)
 * and updating of elements.
 */
public class SegmentTree {
    private final int[] tree;
    private final int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    // Build the tree (O(N))
    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        
        build(arr, leftChild, start, mid);
        build(arr, rightChild, mid + 1, end);
        
        tree[node] = tree[leftChild] + tree[rightChild]; // Change logic for Range Min/Max
    }

    // Point update (O(log N))
    public void update(int idx, int val) {
        updateHelper(0, 0, n - 1, idx, val);
    }

    private void updateHelper(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        
        if (start <= idx && idx <= mid) {
            updateHelper(leftChild, start, mid, idx, val);
        } else {
            updateHelper(rightChild, mid + 1, end, idx, val);
        }
        
        tree[node] = tree[leftChild] + tree[rightChild]; // Change logic for Range Min/Max
    }

    // Range Query (O(log N))
    public int query(int l, int r) {
        return queryHelper(0, 0, n - 1, l, r);
    }

    private int queryHelper(int node, int start, int end, int l, int r) {
        // Completely outside the range
        if (r < start || end < l) {
            return 0; // Return 0 for Sum, Integer.MAX_VALUE for Min
        }
        // Completely inside the range
        if (l <= start && end <= r) {
            return tree[node];
        }
        
        // Partially inside
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        
        int p1 = queryHelper(leftChild, start, mid, l, r);
        int p2 = queryHelper(rightChild, mid + 1, end, l, r);
        
        return p1 + p2; // Change logic for Range Min/Max
    }

    public static void main(String[] args) {
        System.out.println("=== Testing SegmentTree (Range Sum) ===");
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(arr);

        System.out.println("Initial array: [1, 3, 5, 7, 9, 11]");
        System.out.println("Query sum range [1, 3] (3 + 5 + 7) -> Output: " + st.query(1, 3) + " (Expected: 15)");
        System.out.println("Query sum range [0, 5] (full array) -> Output: " + st.query(0, 5) + " (Expected: 36)");

        System.out.println("\nUpdating index 1 to value 10 (arr[1] becomes 10)...");
        st.update(1, 10);
        System.out.println("Query sum range [1, 3] (10 + 5 + 7)-> Output: " + st.query(1, 3) + " (Expected: 22)");
    }
}
