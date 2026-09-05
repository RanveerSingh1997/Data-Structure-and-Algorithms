/**
 * ============================================================================
 * Algorithmic Patterns Database & Invariant Matrix
 * ============================================================================
 * Core data structure patterns, invariant proofs, presets, and solution maps.
 */
const PATTERNS_DATA = [
  {
    id: "two-pointers",
    name: "Two Pointers",
    category: "Arrays & Strings",
    badge: "Core Technique",
    overview: "Pointers initialized at opposite boundaries converging inwards based on directional monotonicity.",
    signalKeywords: ["Opposite Boundaries", "Maximize Area", "Sorted Pairs", "In-Place Traversal"],
    invariantProof: "Width strictly decreases by 1 each step. Limiting height is min(h[L], h[R]). Shorter bar cannot produce a larger area with any remaining inner boundary, so it is provably safe to advance.",
    complexity: "Time: O(N) · Space: O(1)",
    defaultProblem: "Container With Most Water (LeetCode #11)",
    repoPath: "src/arrays/ContainerWithMostWater.java",
    repoClass: "arrays.ContainerWithMostWater",
    presets: [
      { label: "Standard Case", value: "[1, 8, 6, 2, 5, 4, 8, 3, 7]" },
      { label: "Symmetric U-Shape", value: "[4, 3, 2, 1, 4]" },
      { label: "Descending Heights", value: "[9, 7, 5, 3, 1]" }
    ],
    codeSnippet: `public int maxArea(int[] height) {
    int left = 0, right = height.length - 1;
    int maxArea = 0;
    while (left < right) {
        int width = right - left;
        int h = Math.min(height[left], height[right]);
        maxArea = Math.max(maxArea, width * h);
        if (height[left] < height[right]) {
            left++;  // Eliminate bottleneck left
        } else {
            right--; // Eliminate bottleneck right
        }
    }
    return maxArea;
}`,
    curatedProblems: [
      { name: "Container With Most Water", id: 11, difficulty: "Medium", file: "src/arrays/ContainerWithMostWater.java", company: "Google" },
      { name: "3Sum", id: 15, difficulty: "Medium", file: "src/arrays/ThreeSum.java", company: "Google" },
      { name: "Two Sum II - Input Array Is Sorted", id: 167, difficulty: "Medium", file: "src/arrays/TwoSum.java", company: "Amazon" },
      { name: "Valid Palindrome", id: 125, difficulty: "Easy", file: "src/strings/ValidPalindrome.java", company: "Meta" },
      { name: "Trapping Rain Water", id: 42, difficulty: "Hard", file: "src/arrays/TrappingRainWater.java", company: "Google" }
    ]
  },
  {
    id: "sliding-window",
    name: "Sliding Window",
    category: "Strings & Subarrays",
    badge: "High Frequency",
    overview: "Dynamic continuous subsegment [L..R] expanding right and contracting left to maintain window validity.",
    signalKeywords: ["Contiguous Substring", "Unique Characters", "At Most K Distinct", "Window of Size K"],
    invariantProof: "Window [L..R] represents the largest valid unique prefix ending at R. When character s[R] has been seen at index j >= L, all subsegments starting before j + 1 contain a duplicate, so setting L = j + 1 is optimal.",
    complexity: "Time: O(N) · Space: O(min(N, Σ))",
    defaultProblem: "Longest Substring Without Repeating Characters (LeetCode #3)",
    repoPath: "src/strings/LongestSubstringWithoutRepeating.java",
    repoClass: "strings.LongestSubstringWithoutRepeating",
    presets: [
      { label: "Repeated Chars", value: "abcabcbb" },
      { label: "Single Repeating", value: "bbbbb" },
      { label: "Alternating Pattern", value: "pwwkew" }
    ],
    codeSnippet: `public int lengthOfLongestSubstring(String s) {
    int left = 0, maxLen = 0;
    Map<Character, Integer> lastSeen = new HashMap<>();
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
            left = lastSeen.get(c) + 1; // Jump past duplicate
        }
        lastSeen.put(c, right);
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}`,
    curatedProblems: [
      { name: "Longest Substring Without Repeating", id: 3, difficulty: "Medium", file: "src/strings/LongestSubstringWithoutRepeating.java", company: "Google" },
      { name: "Minimum Size Subarray Sum", id: 209, difficulty: "Medium", file: "src/arrays/MinSubArrayLen.java", company: "Meta" },
      { name: "Max Consecutive Ones III", id: 1004, difficulty: "Medium", file: "src/arrays/MaxConsecutiveOnes.java", company: "Google" },
      { name: "Permutation in String", id: 567, difficulty: "Medium", file: "src/strings/PermutationInString.java", company: "Microsoft" },
      { name: "Sliding Window Maximum", id: 239, difficulty: "Hard", file: "src/stacks_queues/SlidingWindowMax.java", company: "Google" }
    ]
  },
  {
    id: "monotonic-stack",
    name: "Monotonic Stack",
    category: "Stacks & Queues",
    badge: "Linear Solver",
    overview: "Stack maintaining strictly sorted elements to resolve nearest greater/smaller neighbor queries in O(1) amortized.",
    signalKeywords: ["Next Greater Element", "Daily Temperatures", "Stock Span", "Histogram Boundaries"],
    invariantProof: "Each element remains on stack until a strictly warmer element is encountered. Popping index j upon seeing index i proves i is the earliest day with temp[i] > temp[j]. Each item enters and leaves stack at most once.",
    complexity: "Time: O(N) · Space: O(N)",
    defaultProblem: "Daily Temperatures (LeetCode #739)",
    repoPath: "src/stacks_queues/DailyTemperatures.java",
    repoClass: "stacks_queues.DailyTemperatures",
    presets: [
      { label: "Standard Weather", value: "[73, 74, 75, 71, 69, 72, 76, 73]" },
      { label: "Steep Warming", value: "[30, 40, 50, 60]" },
      { label: "Cooling Trend", value: "[89, 62, 70, 58, 47]" }
    ],
    codeSnippet: `public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] answer = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
            int prevIdx = stack.pop();
            answer[prevIdx] = i - prevIdx; // Resolve span
        }
        stack.push(i);
    }
    return answer;
}`,
    curatedProblems: [
      { name: "Daily Temperatures", id: 739, difficulty: "Medium", file: "src/stacks_queues/DailyTemperatures.java", company: "Google" },
      { name: "Next Greater Element I", id: 496, difficulty: "Easy", file: "src/stacks_queues/NextGreaterElement.java", company: "Amazon" },
      { name: "Largest Rectangle in Histogram", id: 84, difficulty: "Hard", file: "src/stacks_queues/LargestRectangleHistogram.java", company: "Google" },
      { name: "Online Stock Span", id: 901, difficulty: "Medium", file: "src/stacks_queues/OnlineStockSpan.java", company: "Meta" },
      { name: "Validate Stack Sequences", id: 946, difficulty: "Medium", file: "src/stacks_queues/ValidateStackSequences.java", company: "Google" }
    ]
  },
  {
    id: "binary-search",
    name: "Modified Binary Search",
    category: "Searching & Sorting",
    badge: "Logarithmic Partition",
    overview: "Exploiting sorted half invariant in rotated sequences to bisect search space in O(log N).",
    signalKeywords: ["Rotated Sorted Array", "Logarithmic Search", "Pivot Search", "Search on Answer"],
    invariantProof: "Splitting any rotated sorted array at index mid yields at least one contiguous strictly ordered subarray [low..mid] or [mid..high]. Checking whether target falls within that ordered range deterministically discards half the search space.",
    complexity: "Time: O(log N) · Space: O(1)",
    defaultProblem: "Search in Rotated Sorted Array (LeetCode #33)",
    repoPath: "src/sorting_searching/SearchRotatedSortedArray.java",
    repoClass: "sorting_searching.SearchRotatedSortedArray",
    presets: [
      { label: "Target 0 (Pivot Right)", value: "4, 5, 6, 7, 0, 1, 2; 0" },
      { label: "Target 3 (Not Found)", value: "4, 5, 6, 7, 0, 1, 2; 3" },
      { label: "Target 1 (Pivot Left)", value: "5, 1, 2, 3, 4; 1" }
    ],
    codeSnippet: `public int search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] == target) return mid;
        if (nums[low] <= nums[mid]) {
            if (target >= nums[low] && target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        } else {
            if (target > nums[mid] && target <= nums[high]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }
    return -1;
}`,
    curatedProblems: [
      { name: "Search in Rotated Sorted Array", id: 33, difficulty: "Medium", file: "src/sorting_searching/SearchRotatedSortedArray.java", company: "Google" },
      { name: "Find Minimum in Rotated Sorted Array", id: 153, difficulty: "Medium", file: "src/sorting_searching/FindMinRotated.java", company: "Meta" },
      { name: "Find Peak Element", id: 162, difficulty: "Medium", file: "src/sorting_searching/FindPeakElement.java", company: "Google" },
      { name: "Koko Eating Bananas", id: 875, difficulty: "Medium", file: "src/sorting_searching/KokoEatingBananas.java", company: "Google" }
    ]
  },
  {
    id: "tree-traversal",
    name: "Binary Tree Traversals",
    category: "Trees",
    badge: "Structural Recursion",
    overview: "Recursive divide-and-conquer traversing subtrees to reconstruct, invert, or validate hierarchical data.",
    signalKeywords: ["Invert Binary Tree", "Level Order BFS", "Tree Diameter", "BST Invariant"],
    invariantProof: "For each node, recursively inverting the left subtree and right subtree before or after swapping their pointers guarantees every symmetric relationship across all levels is reflected accurately.",
    complexity: "Time: O(N) · Space: O(H)",
    defaultProblem: "Invert Binary Tree (LeetCode #226)",
    repoPath: "src/trees/InvertBinaryTree.java",
    repoClass: "trees.InvertBinaryTree",
    presets: [
      { label: "Full Binary Tree (3 Levels)", value: "[4, 2, 7, 1, 3, 6, 9]" },
      { label: "Left Skewed", value: "[4, 2, null, 1]" }
    ],
    codeSnippet: `public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    TreeNode temp = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(temp);
    return root;
}`,
    curatedProblems: [
      { name: "Invert Binary Tree", id: 226, difficulty: "Easy", file: "src/trees/InvertBinaryTree.java", company: "Google" },
      { name: "Diameter of Binary Tree", id: 543, difficulty: "Easy", file: "src/trees/DiameterOfBinaryTree.java", company: "Google" },
      { name: "Validate Binary Search Tree", id: 98, difficulty: "Medium", file: "src/trees/ValidateBinarySearchTree.java", company: "Amazon" },
      { name: "Lowest Common Ancestor", id: 236, difficulty: "Medium", file: "src/trees/LowestCommonAncestor.java", company: "Google" }
    ]
  },
  {
    id: "grid-bfs",
    name: "Grid Multi-Source BFS",
    category: "Graphs & Matrix",
    badge: "Wavefront Propagation",
    overview: "Simultaneous multi-source breadth-first search tracking shortest path and infection wave steps across 2D grids.",
    signalKeywords: ["Multi-Source BFS", "Rotting Oranges", "Shortest Path Matrix", "Connected Islands"],
    invariantProof: "Enqueueing all initial sources at t=0 and processing queue in level-sized batches guarantees that any reachable cell is reached in the minimum possible number of minutes/steps.",
    complexity: "Time: O(R · C) · Space: O(R · C)",
    defaultProblem: "Rotting Oranges (LeetCode #994)",
    repoPath: "src/graphs/RottingOranges.java",
    repoClass: "graphs.RottingOranges",
    presets: [
      { label: "Standard Grid (3x3)", value: "2 1 1 ; 1 1 0 ; 0 1 1" },
      { label: "Disconnected Cluster", value: "2 1 1 ; 0 1 1 ; 1 0 1" }
    ],
    codeSnippet: `public int orangesRotting(int[][] grid) {
    int rows = grid.length, cols = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int freshCount = 0;
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            if (grid[r][c] == 2) queue.offer(new int[]{r, c});
            else if (grid[r][c] == 1) freshCount++;
        }
    }
    if (freshCount == 0) return 0;
    int minutes = 0;
    int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    while (!queue.isEmpty() && freshCount > 0) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int[] cell = queue.poll();
            for (int[] d : DIRS) {
                int nr = cell[0] + d[0], nc = cell[1] + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    freshCount--;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        minutes++;
    }
    return freshCount == 0 ? minutes : -1;
}`,
    curatedProblems: [
      { name: "Rotting Oranges", id: 994, difficulty: "Medium", file: "src/graphs/RottingOranges.java", company: "Google" },
      { name: "Number of Islands", id: 200, difficulty: "Medium", file: "src/graphs/NumberOfIslands.java", company: "Google" },
      { name: "Course Schedule", id: 207, difficulty: "Medium", file: "src/graphs/CourseSchedule.java", company: "Google" }
    ]
  },
  {
    id: "merge-intervals",
    name: "Merge Intervals",
    category: "Arrays & Sorting",
    badge: "Greedy Sweep",
    overview: "Start-time ordering followed by single-pass contiguous boundary unification.",
    signalKeywords: ["Overlapping Spans", "Meeting Schedule", "Insert Interval", "Time Ranges"],
    invariantProof: "Once intervals are sorted by start time, any interval that can possibly overlap with interval[i] must have start <= interval[i].end. We either extend the current segment's end or start a new disjoint segment.",
    complexity: "Time: O(N log N) · Space: O(N)",
    defaultProblem: "Merge Intervals (LeetCode #56)",
    repoPath: "src/arrays/MergeIntervals.java",
    repoClass: "arrays.MergeIntervals",
    presets: [
      { label: "Standard Intervals", value: "[[1,3],[2,6],[8,10],[15,18]]" },
      { label: "Enclosed Intervals", value: "[[1,4],[2,3]]" },
      { label: "Chained Overlaps", value: "[[1,4],[4,5],[5,8]]" }
    ],
    codeSnippet: `public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    List<int[]> merged = new ArrayList<>();
    for (int[] interval : intervals) {
        if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
            merged.add(interval);
        } else {
            merged.get(merged.size() - 1)[1] = 
                Math.max(merged.get(merged.size() - 1)[1], interval[1]);
        }
    }
    return merged.toArray(new int[merged.size()][]);
}`,
    curatedProblems: [
      { name: "Merge Intervals", id: 56, difficulty: "Medium", file: "src/arrays/MergeIntervals.java", company: "Google" },
      { name: "Meeting Rooms", id: 252, difficulty: "Easy", file: "src/arrays/MeetingRooms.java", company: "Google" },
      { name: "Insert Interval", id: 57, difficulty: "Medium", file: "src/arrays/InsertInterval.java", company: "Google" }
    ]
  },
  {
    id: "dynamic-programming",
    name: "0/1 Knapsack & Coin Change DP",
    category: "Dynamic Programming",
    badge: "Optimal Substructure",
    overview: "Tabulating subproblem dependencies to compose optimal solutions without redundant re-evaluation.",
    signalKeywords: ["Fewest Elements to Form Target", "Maximum Profit", "Climbing Stairs", "Subsequence Optimization"],
    invariantProof: "dp[i] represents the minimum count to form target i using available choices. Since choices only affect future states non-negatively, Bellman's principle of optimality guarantees optimal subproblem composition.",
    complexity: "Time: O(amount · coins) · Space: O(amount)",
    defaultProblem: "Coin Change (LeetCode #322)",
    repoPath: "src/dp/CoinChange.java",
    repoClass: "dp.CoinChange",
    presets: [
      { label: "Coins [1,2,5] Target 11", value: "[1, 2, 5]; 11" },
      { label: "Coins [2] Target 3 (No Solution)", value: "[2]; 3" }
    ],
    codeSnippet: `public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (i - coin >= 0) {
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
}`,
    curatedProblems: [
      { name: "Coin Change", id: 322, difficulty: "Medium", file: "src/dp/CoinChange.java", company: "Google" },
      { name: "Climbing Stairs", id: 70, difficulty: "Easy", file: "src/dp/ClimbingStairs.java", company: "Amazon" },
      { name: "Longest Increasing Subsequence", id: 300, difficulty: "Medium", file: "src/dp/LIS.java", company: "Google" }
    ]
  }
];

if (typeof window !== "undefined") {
  window.PATTERNS_DATA = PATTERNS_DATA;
}
if (typeof module !== "undefined" && module.exports) {
  module.exports = { PATTERNS_DATA };
}
