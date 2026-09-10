# 🕸️ Advanced Graph Traversals & Topological Sort Guide

Graphs represent networks, dependencies, and grids. In interviews, graph problems usually appear under the guise of scheduling (Course Schedule), infection/fire spread (Rotting Oranges), connectivity (Number of Islands), or shortest paths.

---

## 1. Graph Representation in Java

### Adjacency List (Fastest & Standard)
When vertex IDs are $0 \dots V-1$, an array of lists or `List<List<Integer>>` is significantly faster than a `HashMap` because it avoids hashing overhead:

```java
int numCourses = 4;
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < numCourses; i++) {
    adj.add(new ArrayList<>());
}

// For a directed edge: from -> to (e.g., [to, from] prerequisite pair)
adj.get(from).add(to);
```

---

## 2. Core Graph Patterns

### Pattern 1: Topological Sort via Kahn’s Algorithm (BFS)
* **When to use**: Dependency resolution, task scheduling, or detecting cycles in **Directed Graphs**.
* **Key Concept: In-Degree**:
  - In-degree of node $V$ = the number of edges pointing into $V$ (number of unmet prerequisites).
  - Any node with `inDegree == 0` has **zero remaining dependencies** and is ready to be processed.

#### The Cycle Detection Invariant
> [!IMPORTANT]
> If the graph has a cycle (e.g., $A \to B \to C \to A$), none of the nodes in that cycle will ever reach `inDegree == 0`!
> Therefore, if `processedCount < numNodes`, **a cycle exists** and a valid topological order is impossible.

#### Visual Walkthrough
```
Graph: 0 -> 1 -> 3
       0 -> 2 -> 3

In-degrees:
Node 0: 0
Node 1: 1
Node 2: 1
Node 3: 2

Step 1: Queue starts with in-degree 0: [0]
Step 2: Pop 0. Decrement neighbors 1 and 2:
        In-degrees: [0:0, 1:0, 2:0, 3:2]
        Queue receives nodes with in-degree 0: [1, 2]
Step 3: Pop 1. Decrement neighbor 3 (inDegree becomes 1).
Step 4: Pop 2. Decrement neighbor 3 (inDegree becomes 0). Queue receives: [3]
Step 5: Pop 3.
Total nodes processed = 4 == total vertices -> VALID DAG!
```

#### Generic Template: Kahn's Algorithm
```java
public boolean canFinishCourses(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();
    int[] inDegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
        adj.add(new ArrayList<>());
    }

    // Build graph and calculate in-degrees
    for (int[] edge : prerequisites) {
        int dest = edge[0];
        int src = edge[1];
        adj.get(src).add(dest);
        inDegree[dest]++;
    }

    // Initialize queue with all nodes having 0 incoming dependencies
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (inDegree[i] == 0) {
            queue.offer(i);
        }
    }

    int processedNodes = 0;
    while (!queue.isEmpty()) {
        int curr = queue.poll();
        processedNodes++;

        for (int neighbor : adj.get(curr)) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] == 0) {
                queue.offer(neighbor);
            }
        }
    }

    // If we processed all nodes, there are NO cycles!
    return processedNodes == numCourses;
}
```

---

### Pattern 2: Cycle Detection via DFS 3-Coloring
* **When to use**: Detecting cycles in directed graphs using recursion.
* **The 3 States**:
  - `0 = UNVISITED (White)`: Not yet explored.
  - `1 = VISITING (Gray)`: Currently in the recursive call stack (an ancestor).
  - `2 = VISITED (Black)`: Fully explored and verified safe (no cycles in any descendant branch).

```
If DFS on a neighbor encounters a node in state 1 (VISITING), 
we have discovered a back-edge pointing to an ancestor in our own stack! 
=> CYCLE DETECTED!
```

#### Generic Template
```java
public boolean hasCycleDFS(int numNodes, List<List<Integer>> adj) {
    int[] state = new int[numNodes]; // 0: unvisited, 1: visiting, 2: visited

    for (int i = 0; i < numNodes; i++) {
        if (state[i] == 0) {
            if (hasCycleHelper(i, adj, state)) {
                return true; // Cycle detected
            }
        }
    }
    return false;
}

private boolean hasCycleHelper(int node, List<List<Integer>> adj, int[] state) {
    state[node] = 1; // Mark as currently in recursive stack

    for (int neighbor : adj.get(node)) {
        if (state[neighbor] == 1) {
            return true; // Back-edge to an active ancestor -> CYCLE!
        }
        if (state[neighbor] == 0) {
            if (hasCycleHelper(neighbor, adj, state)) {
                return true;
            }
        }
    }

    state[node] = 2; // Fully processed and safe
    return false;
}
```

---

### Pattern 3: Multi-Source BFS (Simultaneous Spread)
* **When to use**: Fire spreading through a forest, rot spreading through oranges, or finding the distance from any 0 to the nearest 1.
* **The Key Intuition**:
  - In standard BFS, you start with **one** root in the queue.
  - In **Multi-Source BFS**, you find **ALL initial sources** (all rotten oranges, all fires, all gates) and enqueue them all at `t = 0`.
  - Then, step through levels using the snapshot pattern `int size = queue.size()`. All sources expand outward simultaneously layer-by-layer!

```
Initial Grid (t=0):
[ 2 ]  [ 1 ]  [ 0 ]      (2 = Rotten, 1 = Fresh, 0 = Empty)
[ 1 ]  [ 1 ]  [ 2 ]

Queue at start: [(0,0), (1,2)]  <-- BOTH initial sources loaded together

Minute 1:
- (0,0) rots (0,1) and (1,0)
- (1,2) rots (1,1)
Minute 2:
- All fresh oranges are now rotten! Time = 2.
```

#### Generic Template: Multi-Source BFS
```java
public int multiSourceBFS(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int freshCount = 0;

    // 1. Enqueue all starting sources simultaneously
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            if (grid[r][c] == 2) {
                queue.offer(new int[]{r, c});
            } else if (grid[r][c] == 1) {
                freshCount++;
            }
        }
    }

    if (freshCount == 0) return 0; // Nothing to process

    int minutes = 0;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 2. Expand outward layer by layer
    while (!queue.isEmpty() && freshCount > 0) {
        int levelSize = queue.size();
        minutes++;

        for (int i = 0; i < levelSize; i++) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Check bounds and condition
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2; // Mark visited/rotten immediately!
                    freshCount--;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }

    return freshCount == 0 ? minutes : -1;
}
```

---

## 3. High-Frequency Graph Pitfalls in Interviews

### Trap 1: Marking Visited at Dequeue vs. Enqueue
> [!CAUTION]
> In BFS, **ALWAYS mark a node visited IMMEDIATELY when offering it to the queue**, NOT when polling it out!
> If you mark visited at dequeue time, multiple neighbors will re-add the exact same unvisited node into the queue multiple times, causing exponential queue growth and Memory Limit Exceeded (MLE) / Time Limit Exceeded (TLE).

### Trap 2: Undirected vs. Directed Cycle Detection
- In **directed graphs**, a node visited from another branch is NOT necessarily a cycle (it could be a cross-edge). You must track the active recursion stack (via 3-coloring or Kahn's in-degrees).
- In **undirected graphs**, a simple `boolean[] visited` suffices, provided you pass the `parent` node so you don't falsely flag the edge you just came from as a cycle.
