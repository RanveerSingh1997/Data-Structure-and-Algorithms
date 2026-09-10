# 🌲 Binary Trees & BST Patterns Guide

Tree problems are among the most frequently tested topics in Google and FAANG interviews. Almost every tree problem reduces to one of two fundamental mental models: **Top-Down (Pre-order)** or **Bottom-Up (Post-order)**.

---

## 1. The Two Mental Models for Trees

```
        Top-Down (Pre-order)                     Bottom-Up (Post-order)
      "Passing context DOWN"                   "Bubbling metrics UP"
             [ Root ]                                  [ Root ]
             /      \                                  ^      ^
            v        v                                 |      |
        [Left]      [Right]                        [Left]    [Right]
  Pass down bounds/depth to children             Children return height/sum to parent
```

| Traversal Style | Mental Model | When to Use | Classic Problems |
| :--- | :--- | :--- | :--- |
| **Top-Down (Pre-order)** | Parent calculates state and passes it *down* to children via recursive parameters | Validating BST bounds, root-to-leaf path sums, tree serialization | Validate BST, Path Sum, Invert Binary Tree |
| **Bottom-Up (Post-order)** | Leaves return answers to parents, parent merges child answers and passes *up* | Height, subtree sizes, diameter, tree deletion, LCA | Diameter of Binary Tree, LCA, Balanced Tree |

---

## 2. Core Tree Patterns & Templates

### Pattern 1: Bottom-Up DFS (Post-Order Metric Aggregation)
* **The Concept**: To solve a problem for node $X$, you first need the answers from both $X.\text{left}$ and $X.\text{right}$.
* **Key Invariant**: The recursive helper returns one metric (e.g. *depth*), while updating a global tracker for a different metric (e.g. *diameter* or *maximum path sum*).

#### Visualizing Diameter
The diameter at any node is $\text{leftHeight} + \text{rightHeight}$.
```
           1 (Height: 3, Local Diameter: 2 + 1 = 3)
          / \
         2   3 (Height: 1)
        / \
       4   5 (Height: 1)
      /
     6 (Height: 1)

Longest path passes through node 2: [6 -> 4 -> 2 -> 5] (Length 3 edges)
```

#### Generic Template
```java
public class TreeDiameterPattern {
    private int maxMetric = 0;

    public int findMaxMetric(TreeNode root) {
        maxMetric = 0;
        bottomUpHelper(root);
        return maxMetric;
    }

    private int bottomUpHelper(TreeNode node) {
        if (node == null) return 0; // Base case: null node has 0 depth

        // 1. Recurse down both subtrees
        int left = bottomUpHelper(node.left);
        int right = bottomUpHelper(node.right);

        // 2. Combine child results at current node to update global answer
        maxMetric = Math.max(maxMetric, left + right);

        // 3. Return the single branch contribution back up to the parent
        return 1 + Math.max(left, right);
    }
}
```

---

### Pattern 2: Top-Down DFS with Invariant Bounds (Validate BST)
* **The Pitfall**: A naive check `node.val > node.left.val && node.val < node.right.val` **fails** because it only checks immediate children, not entire subtrees!

```
          5
         / \
        1   6
           / \
         (3)  7   <-- INVALID! 3 is in 5's right subtree, but 3 < 5!
```

* **The Invariant**: Every node in a BST must fall strictly inside an allowed interval: $(\text{lowerBound}, \text{upperBound})$.
  - When moving **left**: the new upper bound becomes `node.val`.
  - When moving **right**: the new lower bound becomes `node.val`.
* **Overflow Trap**: If tree nodes contain `Integer.MIN_VALUE` or `Integer.MAX_VALUE`, standard `int` bounds cause overflow. Always use `Long.MIN_VALUE` and `Long.MAX_VALUE`!

#### Generic Template
```java
public boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

private boolean validate(TreeNode node, long min, long max) {
    if (node == null) return true; // Empty tree is valid

    // Check current node against inherited bounds
    if (node.val <= min || node.val >= max) {
        return false;
    }

    // Left child must be in range (min, node.val)
    // Right child must be in range (node.val, max)
    return validate(node.left, min, node.val) && validate(node.right, node.val, max);
}
```

---

### Pattern 3: Lowest Common Ancestor (LCA) Decision Tree
* **The Concept**: Find the lowest node that has both node $P$ and node $Q$ as descendants.
* **Three Decision Cases at Any Node `curr`**:
  1. If `curr == null`, or `curr == p`, or `curr == q` $\implies$ return `curr`.
  2. Search both subtrees: `left = lca(curr.left)`, `right = lca(curr.right)`.
  3. Combine results:
     - If both `left != null` AND `right != null`: $P$ is in one subtree and $Q$ is in the other $\implies$ **`curr` is the LCA!**
     - If only one is non-null: return that non-null node (both targets lie in that branch).
     - If both are null: return `null`.

```
           3
          / \
        (5)   1
        / \
       6  (2)

Find LCA of 5 and 2:
- At node 2: returns 2 (found Q)
- At node 6: returns null
- At node 5: left=null, right=2, but node 5 IS target P! So node 5 returns 5.
- At node 1: returns null
- At node 3: left=5, right=null -> returns 5. LCA is 5!
```

#### Generic Template
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Base Case: null node or matched one of the targets
    if (root == null || root == p || root == q) {
        return root;
    }

    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    // Both returned non-null -> root is the split point (LCA)
    if (left != null && right != null) {
        return root;
    }

    // Otherwise, return whichever side found a target (or null)
    return left != null ? left : right;
}
```

---

### Pattern 4: Level-Order Traversal (BFS Snapshot Pattern)
* **When to use**: Shortest path in trees, viewing a tree level-by-level, zig-zag traversals, right/left side views.
* **Key Invariant**: Record `int levelSize = queue.size()` before the loop. This freezes the level boundary so newly enqueued children don't bleed into the current level.

```
Queue State before level: [Node A, Node B] -> levelSize = 2
Process Node A -> Push its children (C, D)
Process Node B -> Push its children (E)
Loop finishes after exactly 2 iterations!
Queue State for next level: [Node C, Node D, Node E]
```

#### Generic Template
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size(); // SNAPSHOT the current level size
        List<Integer> currentLevel = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
            TreeNode curr = queue.poll();
            currentLevel.add(curr.val);

            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        result.add(currentLevel);
    }
    return result;
}
```

---

## 3. Tree Traversal Invariants Summary

| Traversal | Order | Invariant / Key Property |
| :--- | :--- | :--- |
| **In-Order** | Left $\to$ Root $\to$ Right | For a BST, visits nodes in **strictly ascending order**! |
| **Pre-Order** | Root $\to$ Left $\to$ Right | Parent is processed before any of its descendants (ideal for serialization / copying). |
| **Post-Order** | Left $\to$ Right $\to$ Root | All descendants are fully processed before the parent (ideal for deletion, bottom-up metrics). |
| **Level-Order** | Level by level (Top $\to$ Bottom) | Finds the shortest path in an unweighted tree. |
