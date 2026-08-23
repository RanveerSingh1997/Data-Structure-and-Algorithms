# Algorithm Detailed Refresh Guide

Use this document to rapidly recall core algorithmic concepts, why they work, and their standard bug-free templates right before an interview.

---

## 1. Binary Search

### The Concept
Binary search finds a target in a **sorted array** in `O(log N)` time by repeatedly dividing the search space in half. 

### Common Pitfalls to Avoid
- **Integer Overflow**: Never use `mid = (left + right) / 2`. If `left` and `right` are massive, their sum exceeds the integer limit. Always use `mid = left + (right - left) / 2`.
- **Infinite Loops**: Using `while (left < right)` can cause infinite loops if you aren't careful with how you update `left` and `right`. The `while (left <= right)` template is much safer for exact matching.

### The Standard Template (Exact Match)
```java
public int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1; 
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            return mid; // Found it!
        } else if (nums[mid] < target) {
            left = mid + 1; // Target is to the right
        } else {
            right = mid - 1; // Target is to the left
        }
    }
    return -1; // Target not found
}
```

---

## 2. Graph Traversals

### Breadth-First Search (BFS)
**Best for**: Finding the shortest path on an unweighted graph, or exploring a tree level-by-level.
**Data Structure**: Queue (FIFO).

**Detailed Template**:
```java
public void bfs(int startNode, List<List<Integer>> adjList, int numNodes) {
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[numNodes];
    
    q.offer(startNode);
    visited[startNode] = true; // Mark visited IMMEDIATELY when adding to queue
    
    int distance = 0; // Tracks the shortest path distance
    
    while (!q.isEmpty()) {
        int size = q.size();
        
        // This inner loop processes all nodes at the current "level"
        for (int i = 0; i < size; i++) { 
            int curr = q.poll();
            System.out.println("Processing node: " + curr);
            
            for (int neighbor : adjList.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(neighbor);
                }
            }
        }
        distance++; // Increment after fully processing a level
    }
}
```

### Depth-First Search (DFS)
**Best for**: Backtracking, finding connected components, detecting cycles, exploring all possible paths.
**Data Structure**: Call Stack (Recursion) or explicit Stack (LIFO).

**Detailed Template**:
```java
public void dfs(int node, List<List<Integer>> adjList, boolean[] visited) {
    visited[node] = true; // Mark as visited upon entering
    
    // Process current node here
    System.out.println("Visited: " + node);
    
    for (int neighbor : adjList.get(node)) {
        if (!visited[neighbor]) {
            dfs(neighbor, adjList, visited);
        }
    }
}
```

---

## 3. The Backtracking Template
Backtracking is essentially DFS on a decision tree. It is used to generate all permutations, combinations, or subsets.

### The Concept
1. **Choose**: Make a choice (add element to temporary list).
2. **Explore**: Recurse further down the decision tree.
3. **Un-choose**: Undo the choice (remove element from temporary list) so you can try a different path.

### Detailed Template (Finding all Subsets)
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, 0);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
    // 1. Base Case: Add a copy of the current state to the result
    result.add(new ArrayList<>(tempList)); 
    
    // 2. Iterate through available choices
    for (int i = start; i < nums.length; i++) {
        // 3. Make a choice
        tempList.add(nums[i]);
        
        // 4. Explore further (pass 'i + 1' so we don't reuse the same element)
        backtrack(result, tempList, nums, i + 1); 
        
        // 5. Undo the choice (Backtrack)
        tempList.remove(tempList.size() - 1);
    }
}
```

---

## 4. Sliding Window Template
**Best for**: Subarray or Substring problems (e.g., "Find the longest substring without repeating characters").

### Detailed Template
```java
public int lengthOfLongestSubstring(String s) {
    int left = 0, right = 0;
    int maxLen = 0;
    Set<Character> window = new HashSet<>();
    
    while (right < s.length()) {
        char rightChar = s.charAt(right);
        
        // 1. Shrink window from the left if it becomes invalid
        // Example: The set already contains this character (a duplicate!)
        while (window.contains(rightChar)) {
            char leftChar = s.charAt(left);
            window.remove(leftChar);
            left++;
        }
        
        // 2. Add current character to window state
        window.add(rightChar);
        
        // 3. Update answer
        maxLen = Math.max(maxLen, right - left + 1);
        
        // 4. Expand window
        right++;
    }
    return maxLen;
}
```

---

## 5. Monotonic Stack Template
**Best for**: "Next Greater Element" problems. A monotonic stack is simply a stack whose elements are strictly increasing or strictly decreasing.

### Detailed Template (Next Greater Element)
```java
public int[] nextGreaterElement(int[] nums) {
    int[] nextGreater = new int[nums.length];
    Arrays.fill(nextGreater, -1); // Default if no greater element exists
    
    Stack<Integer> stack = new Stack<>(); // Store INDICES, not values!
    
    for (int i = 0; i < nums.length; i++) {
        // While stack is not empty and the current element is greater 
        // than the element at the index stored on top of the stack
        while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
            int prevIndex = stack.pop();
            // The current element is the "Next Greater" for the popped index
            nextGreater[prevIndex] = nums[i]; 
        }
        // Push the current index to be resolved later
        stack.push(i);
    }
    
    return nextGreater;
}
```

---

## 6. Dynamic Programming (DP) Concepts

### Top-Down (Memoization)
You start at the final goal and break it down recursively. To prevent solving the same subproblem twice, you cache the result in an array or HashMap.
*Pros*: Usually easier to think about if you understand recursion. You only compute states you actually visit.
*Cons*: Can cause StackOverflow for very deep recursion trees.

### Bottom-Up (Tabulation)
You start from the base cases (e.g., `dp[0]` and `dp[1]`) and use a `for` loop to build up the solution sequentially up to `dp[N]`.
*Pros*: No recursion overhead. Excellent space complexity (often a 2D array can be optimized to a 1D array or just two variables).
*Cons*: You must visit every single state, even if the optimal path wouldn't require it.
