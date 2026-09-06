/**
 * ============================================================================
 * Algorithmic Patterns Database & Invariant Matrix
 * ============================================================================
 * Production-ready catalog of core data structure patterns, invariant proofs,
 * testing presets, Big-O complexities, clarifying questions, and interview traps.
 */
const PATTERNS_DATA = [
  {
    "id": "two-pointers",
    "name": "Two Pointers",
    "category": "Arrays & Strings",
    "badge": "Core Technique",
    "overview": "Pointers initialized at opposite boundaries converging inwards based on directional monotonicity.",
    "signalKeywords": [
      "Opposite Boundaries",
      "Maximize Area",
      "Sorted Pairs",
      "In-Place Traversal"
    ],
    "invariantProof": "Width strictly decreases by 1 each step. Limiting height is min(h[L], h[R]). Shorter bar cannot produce a larger area with any remaining inner boundary, so it is provably safe to advance.",
    "complexity": "Time: O(N) · Space: O(1)",
    "defaultProblem": "Container With Most Water (LeetCode #11)",
    "repoPath": "src/arrays/ContainerWithMostWater.java",
    "repoClass": "arrays.ContainerWithMostWater",
    "presets": [
      {
        "label": "Standard Case",
        "value": "[1, 8, 6, 2, 5, 4, 8, 3, 7]"
      },
      {
        "label": "Symmetric U-Shape",
        "value": "[4, 3, 2, 1, 4]"
      },
      {
        "label": "Descending Heights",
        "value": "[9, 7, 5, 3, 1]"
      }
    ],
    "codeSnippet": "public int maxArea(int[] height) {\n    int left = 0, right = height.length - 1;\n    int maxArea = 0;\n    while (left < right) {\n        int width = right - left;\n        int h = Math.min(height[left], height[right]);\n        maxArea = Math.max(maxArea, width * h);\n        if (height[left] < height[right]) {\n            left++;  // Eliminate bottleneck left\n        } else {\n            right--; // Eliminate bottleneck right\n        }\n    }\n    return maxArea;\n}",
    "curatedProblems": [
      {
        "name": "Container With Most Water",
        "id": 11,
        "difficulty": "Medium",
        "file": "src/arrays/ContainerWithMostWater.java",
        "company": "Google"
      },
      {
        "name": "3Sum",
        "id": 15,
        "difficulty": "Medium",
        "file": "src/arrays/ThreeSum.java",
        "company": "Google"
      },
      {
        "name": "Two Sum II - Input Array Is Sorted",
        "id": 167,
        "difficulty": "Medium",
        "file": "src/arrays/TwoSum.java",
        "company": "Amazon"
      },
      {
        "name": "Valid Palindrome",
        "id": 125,
        "difficulty": "Easy",
        "file": "src/strings/ValidPalindrome.java",
        "company": "Meta"
      },
      {
        "name": "Trapping Rain Water",
        "id": 42,
        "difficulty": "Hard",
        "file": "src/arrays/TrappingRainWater.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Sorted array / Pair matching / In-place sweep ⇒ N ≤ 10^5 demands O(N) single pass",
    "clarifyingQuestions": [
      "Can the input array contain negative values or all zeros?",
      "Is the array guaranteed to have at least 2 elements?",
      "Can we modify the input array in-place or is it read-only?"
    ],
    "interviewTraps": [
      "Advancing the taller boundary instead of the shorter bottleneck (shorter bar strictly caps area).",
      "Using nested O(N^2) loops when directional monotonicity enables O(N).",
      "Premature termination before left and right pointers converge."
    ],
    "complexityDeepDive": {
      "time": "O(N) - In every step, either left advances or right retreats, yielding exactly N-1 iterations.",
      "space": "O(1) - Only two integer pointer variables (left, right) are maintained."
    },
    "starterTemplate": "class Solution {\n    public int maxArea(int[] height) {\n        int left = 0, right = height.length - 1;\n        int maxArea = 0;\n        while (left < right) {\n            // TODO: Compute container area and advance bottleneck pointer\n        }\n        return maxArea;\n    }\n}"
  },
  {
    "id": "sliding-window",
    "name": "Sliding Window",
    "category": "Arrays & Strings",
    "badge": "High Frequency",
    "overview": "Dynamic continuous subsegment [L..R] expanding right and contracting left to maintain window validity.",
    "signalKeywords": [
      "Contiguous Substring",
      "Unique Characters",
      "At Most K Distinct",
      "Window of Size K"
    ],
    "invariantProof": "Window [L..R] represents the largest valid unique prefix ending at R. When character s[R] has been seen at index j >= L, all subsegments starting before j + 1 contain a duplicate, so setting L = j + 1 is optimal.",
    "complexity": "Time: O(N) · Space: O(min(N, Σ))",
    "defaultProblem": "Longest Substring Without Repeating Characters (LeetCode #3)",
    "repoPath": "src/strings/LongestSubstringWithoutRepeating.java",
    "repoClass": "strings.LongestSubstringWithoutRepeating",
    "presets": [
      {
        "label": "Repeated Chars",
        "value": "abcabcbb"
      },
      {
        "label": "Single Repeating",
        "value": "bbbbb"
      },
      {
        "label": "Alternating Pattern",
        "value": "pwwkew"
      }
    ],
    "codeSnippet": "public int lengthOfLongestSubstring(String s) {\n    int left = 0, maxLen = 0;\n    Map<Character, Integer> lastSeen = new HashMap<>();\n    for (int right = 0; right < s.length(); right++) {\n        char c = s.charAt(right);\n        if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {\n            left = lastSeen.get(c) + 1; // Jump past duplicate\n        }\n        lastSeen.put(c, right);\n        maxLen = Math.max(maxLen, right - left + 1);\n    }\n    return maxLen;\n}",
    "curatedProblems": [
      {
        "name": "Longest Substring Without Repeating",
        "id": 3,
        "difficulty": "Medium",
        "file": "src/strings/LongestSubstringWithoutRepeating.java",
        "company": "Google"
      },
      {
        "name": "Minimum Size Subarray Sum",
        "id": 209,
        "difficulty": "Medium",
        "file": "src/arrays/MinSubArrayLen.java",
        "company": "Meta"
      },
      {
        "name": "Max Consecutive Ones III",
        "id": 1004,
        "difficulty": "Medium",
        "file": "src/arrays/MaxConsecutiveOnes.java",
        "company": "Google"
      },
      {
        "name": "Permutation in String",
        "id": 567,
        "difficulty": "Medium",
        "file": "src/strings/PermutationInString.java",
        "company": "Microsoft"
      },
      {
        "name": "Sliding Window Maximum",
        "id": 239,
        "difficulty": "Hard",
        "file": "src/stacks_queues/SlidingWindowMax.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Contiguous subarray/substring with condition ⇒ Expand right, shrink left when breached",
    "clarifyingQuestions": [
      "Does the string contain only lowercase English letters or full ASCII/Unicode?",
      "Can the input string be empty or length 1?",
      "Are we looking for the longest, shortest, or exact-length window?"
    ],
    "interviewTraps": [
      "Resetting left to prevIdx instead of prevIdx + 1, triggering infinite loops.",
      "Forgetting to check lastSeen.get(c) >= left before updating left (can move left backwards!).",
      "Using O(N^3) brute-force substring generation instead of amortized O(N) sliding window."
    ],
    "complexityDeepDive": {
      "time": "O(N) - Each character enters the window via right and leaves at most once via left.",
      "space": "O(min(N, Σ)) - Space bounded by unique characters in string or alphabet size Σ."
    },
    "starterTemplate": "class Solution {\n    public int lengthOfLongestSubstring(String s) {\n        Map<Character, Integer> lastSeen = new HashMap<>();\n        int left = 0, maxLen = 0;\n        for (int right = 0; right < s.length(); right++) {\n            // TODO: Maintain unique window invariant and track maxLen\n        }\n        return maxLen;\n    }\n}"
  },
  {
    "id": "monotonic-stack",
    "name": "Monotonic Stack",
    "category": "Stacks & Queues",
    "badge": "Linear Solver",
    "overview": "Stack maintaining strictly sorted elements to resolve nearest greater/smaller neighbor queries in O(1) amortized.",
    "signalKeywords": [
      "Next Greater Element",
      "Daily Temperatures",
      "Stock Span",
      "Histogram Boundaries"
    ],
    "invariantProof": "Each element remains on stack until a strictly warmer element is encountered. Popping index j upon seeing index i proves i is the earliest day with temp[i] > temp[j]. Each item enters and leaves stack at most once.",
    "complexity": "Time: O(N) · Space: O(N)",
    "defaultProblem": "Daily Temperatures (LeetCode #739)",
    "repoPath": "src/stacks_queues/DailyTemperatures.java",
    "repoClass": "stacks_queues.DailyTemperatures",
    "presets": [
      {
        "label": "Standard Weather",
        "value": "[73, 74, 75, 71, 69, 72, 76, 73]"
      },
      {
        "label": "Steep Warming",
        "value": "[30, 40, 50, 60]"
      },
      {
        "label": "Cooling Trend",
        "value": "[89, 62, 70, 58, 47]"
      }
    ],
    "codeSnippet": "public int[] dailyTemperatures(int[] temperatures) {\n    int n = temperatures.length;\n    int[] answer = new int[n];\n    Deque<Integer> stack = new ArrayDeque<>();\n    for (int i = 0; i < n; i++) {\n        while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {\n            int prevIdx = stack.pop();\n            answer[prevIdx] = i - prevIdx; // Resolve span\n        }\n        stack.push(i);\n    }\n    return answer;\n}",
    "curatedProblems": [
      {
        "name": "Daily Temperatures",
        "id": 739,
        "difficulty": "Medium",
        "file": "src/stacks_queues/DailyTemperatures.java",
        "company": "Google"
      },
      {
        "name": "Next Greater Element I",
        "id": 496,
        "difficulty": "Easy",
        "file": "src/stacks_queues/NextGreaterElement.java",
        "company": "Amazon"
      },
      {
        "name": "Largest Rectangle in Histogram",
        "id": 84,
        "difficulty": "Hard",
        "file": "src/stacks_queues/LargestRectangleHistogram.java",
        "company": "Google"
      },
      {
        "name": "Online Stock Span",
        "id": 901,
        "difficulty": "Medium",
        "file": "src/stacks_queues/OnlineStockSpan.java",
        "company": "Meta"
      },
      {
        "name": "Validate Stack Sequences",
        "id": 946,
        "difficulty": "Medium",
        "file": "src/stacks_queues/ValidateStackSequences.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Nearest greater/smaller element in O(1) amortized ⇒ Stack maintains strictly sorted values",
    "clarifyingQuestions": [
      "What should be returned if no warmer/greater element exists? (Usually 0 or -1)",
      "Can elements contain duplicate values?",
      "Is input length up to 10^5? (Precludes O(N^2) nested loops)"
    ],
    "interviewTraps": [
      "Storing raw values on stack instead of indices (indices allow calculating distance i - prevIdx).",
      "Using <= instead of < when problem requires strictly greater elements.",
      "Failing to initialize unanswered entries with default sentinel values."
    ],
    "complexityDeepDive": {
      "time": "O(N) amortized - Every index is pushed exactly once and popped at most once.",
      "space": "O(N) - Monotonically decreasing input leaves all N indices on the stack."
    },
    "starterTemplate": "class Solution {\n    public int[] dailyTemperatures(int[] temperatures) {\n        int n = temperatures.length;\n        int[] ans = new int[n];\n        Deque<Integer> stack = new ArrayDeque<>();\n        for (int i = 0; i < n; i++) {\n            // TODO: Pop cooler indices and record span\n        }\n        return ans;\n    }\n}"
  },
  {
    "id": "binary-search",
    "name": "Modified Binary Search",
    "category": "Arrays & Strings",
    "badge": "Logarithmic Partition",
    "overview": "Exploiting sorted half invariant in rotated sequences to bisect search space in O(log N).",
    "signalKeywords": [
      "Rotated Sorted Array",
      "Logarithmic Search",
      "Pivot Search",
      "Search on Answer"
    ],
    "invariantProof": "Splitting any rotated sorted array at index mid yields at least one contiguous strictly ordered subarray [low..mid] or [mid..high]. Checking whether target falls within that ordered range deterministically discards half the search space.",
    "complexity": "Time: O(log N) · Space: O(1)",
    "defaultProblem": "Search in Rotated Sorted Array (LeetCode #33)",
    "repoPath": "src/sorting_searching/SearchRotatedSortedArray.java",
    "repoClass": "sorting_searching.SearchRotatedSortedArray",
    "presets": [
      {
        "label": "Target 0 (Pivot Right)",
        "value": "4, 5, 6, 7, 0, 1, 2; 0"
      },
      {
        "label": "Target 3 (Not Found)",
        "value": "4, 5, 6, 7, 0, 1, 2; 3"
      },
      {
        "label": "Target 1 (Pivot Left)",
        "value": "5, 1, 2, 3, 4; 1"
      }
    ],
    "codeSnippet": "public int search(int[] nums, int target) {\n    int low = 0, high = nums.length - 1;\n    while (low <= high) {\n        int mid = low + (high - low) / 2;\n        if (nums[mid] == target) return mid;\n        if (nums[low] <= nums[mid]) {\n            if (target >= nums[low] && target < nums[mid]) {\n                high = mid - 1;\n            } else {\n                low = mid + 1;\n            }\n        } else {\n            if (target > nums[mid] && target <= nums[high]) {\n                low = mid + 1;\n            } else {\n                high = mid - 1;\n            }\n        }\n    }\n    return -1;\n}",
    "curatedProblems": [
      {
        "name": "Search in Rotated Sorted Array",
        "id": 33,
        "difficulty": "Medium",
        "file": "src/sorting_searching/SearchRotatedSortedArray.java",
        "company": "Google"
      },
      {
        "name": "Find Minimum in Rotated Sorted Array",
        "id": 153,
        "difficulty": "Medium",
        "file": "src/sorting_searching/FindMinRotated.java",
        "company": "Meta"
      },
      {
        "name": "Find Peak Element",
        "id": 162,
        "difficulty": "Medium",
        "file": "src/sorting_searching/FindPeakElement.java",
        "company": "Google"
      },
      {
        "name": "Koko Eating Bananas",
        "id": 875,
        "difficulty": "Medium",
        "file": "src/sorting_searching/KokoEatingBananas.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Sorted or rotated monotonic space ⇒ O(log N) partition by inspecting mid",
    "clarifyingQuestions": [
      "Are duplicate elements present in the rotated array?",
      "Can the array be unrotated (already sorted)?",
      "What return value is expected if target is absent? (-1)"
    ],
    "interviewTraps": [
      "Integer overflow computing (low + high) / 2. Always use low + (high - low) / 2.",
      "Off-by-one errors on <= vs < when checking if target lies within the ordered half.",
      "Forgetting that splitting any rotated sorted array always yields at least one sorted half."
    ],
    "complexityDeepDive": {
      "time": "O(log N) - Each step bisects search interval by at least 50%.",
      "space": "O(1) - Iterative pointer manipulation without recursion stack."
    },
    "starterTemplate": "class Solution {\n    public int search(int[] nums, int target) {\n        int low = 0, high = nums.length - 1;\n        while (low <= high) {\n            int mid = low + (high - low) / 2;\n            // TODO: Identify sorted half and prune\n        }\n        return -1;\n    }\n}"
  },
  {
    "id": "fast-slow-pointers",
    "name": "Fast & Slow Pointers",
    "category": "Linked Lists",
    "badge": "Cycle Detection",
    "overview": "Two pointers advancing at different speeds (1x vs 2x) to detect loops and locate list midpoints.",
    "signalKeywords": [
      "Cycle Detection",
      "Linked List Loop",
      "Find Middle Node",
      "Happy Number"
    ],
    "invariantProof": "Floyd's Tortoise and Hare algorithm: In an acyclic list, fast reaches null in N/2 steps. In a cyclic list of loop length C, the relative distance between fast and slow increases by 1 step per iteration, guaranteeing meeting within C steps.",
    "complexity": "Time: O(N) · Space: O(1)",
    "defaultProblem": "Linked List Cycle (LeetCode #141)",
    "repoPath": "src/linked_list/FindLoop.java",
    "repoClass": "linked_list.FindLoop",
    "presets": [
      {
        "label": "Cycle at Node 3",
        "value": "1->2->3->4->5->6->3"
      },
      {
        "label": "Acyclic List (No Loop)",
        "value": "1->2->3->4->5->null"
      },
      {
        "label": "Tiny Cycle (2 Nodes)",
        "value": "1->2->1"
      }
    ],
    "codeSnippet": "public boolean hasCycle(ListNode head) {\n    if (head == null || head.next == null) return false;\n    ListNode slow = head;\n    ListNode fast = head;\n    while (fast != null && fast.next != null) {\n        slow = slow.next;         // 1 step\n        fast = fast.next.next;    // 2 steps\n        if (slow == fast) {\n            return true;          // Cycle detected!\n        }\n    }\n    return false;                 // Reached end of list\n}",
    "curatedProblems": [
      {
        "name": "Linked List Cycle",
        "id": 141,
        "difficulty": "Easy",
        "file": "src/linked_list/FindLoop.java",
        "company": "Amazon"
      },
      {
        "name": "Middle of the Linked List",
        "id": 876,
        "difficulty": "Easy",
        "file": "src/linked_list/FindMiddleNode.java",
        "company": "Google"
      },
      {
        "name": "Linked List Cycle II (Find Start)",
        "id": 142,
        "difficulty": "Medium",
        "file": "src/linked_list/FindLoop.java",
        "company": "Meta"
      },
      {
        "name": "Happy Number",
        "id": 202,
        "difficulty": "Easy",
        "file": "src/linked_list/FindLoop.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Cycle detection & list midpoints without extra memory ⇒ Slow moves 1x, Fast moves 2x",
    "clarifyingQuestions": [
      "Can the head pointer be null or have only 1 node?",
      "Can node values have duplicates? (Compare object references slow == fast, not values!)",
      "Is the list allowed to be modified during traversal?"
    ],
    "interviewTraps": [
      "Null pointer exception: failing to check fast != null && fast.next != null before fast.next.next.",
      "Comparing slow.val == fast.val instead of pointer equality slow == fast.",
      "Using a HashSet when interview specifies strict O(1) auxiliary space constraint."
    ],
    "complexityDeepDive": {
      "time": "O(N) - Fast reaches null in N/2 steps if acyclic; closes gap within C steps if cyclic.",
      "space": "O(1) - Only two node references maintained."
    },
    "starterTemplate": "public class Solution {\n    public boolean hasCycle(ListNode head) {\n        if (head == null || head.next == null) return false;\n        ListNode slow = head, fast = head;\n        while (fast != null && fast.next != null) {\n            // TODO: Advance slow 1x and fast 2x\n        }\n        return false;\n    }\n}"
  },
  {
    "id": "tree-traversal",
    "name": "Binary Tree Traversals",
    "category": "Trees & Graphs",
    "badge": "Structural Recursion",
    "overview": "Recursive divide-and-conquer traversing subtrees to reconstruct, invert, or validate hierarchical data.",
    "signalKeywords": [
      "Invert Binary Tree",
      "Level Order BFS",
      "Tree Diameter",
      "BST Invariant"
    ],
    "invariantProof": "For each node, recursively inverting the left subtree and right subtree before or after swapping their pointers guarantees every symmetric relationship across all levels is reflected accurately.",
    "complexity": "Time: O(N) · Space: O(H)",
    "defaultProblem": "Invert Binary Tree (LeetCode #226)",
    "repoPath": "src/trees/InvertBinaryTree.java",
    "repoClass": "trees.InvertBinaryTree",
    "presets": [
      {
        "label": "Full Binary Tree (3 Levels)",
        "value": "[4, 2, 7, 1, 3, 6, 9]"
      },
      {
        "label": "Left Skewed",
        "value": "[4, 2, null, 1]"
      }
    ],
    "codeSnippet": "public TreeNode invertTree(TreeNode root) {\n    if (root == null) return null;\n    TreeNode temp = root.left;\n    root.left = invertTree(root.right);\n    root.right = invertTree(temp);\n    return root;\n}",
    "curatedProblems": [
      {
        "name": "Invert Binary Tree",
        "id": 226,
        "difficulty": "Easy",
        "file": "src/trees/InvertBinaryTree.java",
        "company": "Google"
      },
      {
        "name": "Diameter of Binary Tree",
        "id": 543,
        "difficulty": "Easy",
        "file": "src/trees/DiameterOfBinaryTree.java",
        "company": "Google"
      },
      {
        "name": "Validate Binary Search Tree",
        "id": 98,
        "difficulty": "Medium",
        "file": "src/trees/ValidateBinarySearchTree.java",
        "company": "Amazon"
      },
      {
        "name": "Lowest Common Ancestor",
        "id": 236,
        "difficulty": "Medium",
        "file": "src/trees/LowestCommonAncestor.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Divide and conquer on subtrees ⇒ Process root, recurse left, recurse right",
    "clarifyingQuestions": [
      "Can the tree be empty (root == null)?",
      "Is the tree balanced or potentially degenerate (skewed line)?",
      "Should the inversion/traversal be done in-place or create new nodes?"
    ],
    "interviewTraps": [
      "Overwriting root.left before caching it, losing access to the original left subtree.",
      "Stack overflow on deeply skewed trees (maximum depth O(N)).",
      "Mixing up pre-order (root-L-R) and post-order (L-R-root) recursive semantics."
    ],
    "complexityDeepDive": {
      "time": "O(N) - Every node in the binary tree is visited exactly once.",
      "space": "O(H) - Call stack depth equals tree height H (O(log N) balanced, O(N) worst-case skewed)."
    },
    "starterTemplate": "class Solution {\n    public TreeNode invertTree(TreeNode root) {\n        if (root == null) return null;\n        // TODO: Swap subtrees recursively\n        return root;\n    }\n}"
  },
  {
    "id": "grid-bfs",
    "name": "Grid Multi-Source BFS",
    "category": "Trees & Graphs",
    "badge": "Wavefront Propagation",
    "overview": "Simultaneous multi-source breadth-first search tracking shortest path and infection wave steps across 2D grids.",
    "signalKeywords": [
      "Multi-Source BFS",
      "Rotting Oranges",
      "Shortest Path Matrix",
      "Connected Islands"
    ],
    "invariantProof": "Enqueueing all initial sources at t=0 and processing queue in level-sized batches guarantees that any reachable cell is reached in the minimum possible number of minutes/steps.",
    "complexity": "Time: O(R · C) · Space: O(R · C)",
    "defaultProblem": "Rotting Oranges (LeetCode #994)",
    "repoPath": "src/graphs/RottingOranges.java",
    "repoClass": "graphs.RottingOranges",
    "presets": [
      {
        "label": "Standard Grid (3x3)",
        "value": "2 1 1 ; 1 1 0 ; 0 1 1"
      },
      {
        "label": "Disconnected Cluster",
        "value": "2 1 1 ; 0 1 1 ; 1 0 1"
      }
    ],
    "codeSnippet": "public int orangesRotting(int[][] grid) {\n    int rows = grid.length, cols = grid[0].length;\n    Queue<int[]> queue = new LinkedList<>();\n    int freshCount = 0;\n    for (int r = 0; r < rows; r++) {\n        for (int c = 0; c < cols; c++) {\n            if (grid[r][c] == 2) queue.offer(new int[]{r, c});\n            else if (grid[r][c] == 1) freshCount++;\n        }\n    }\n    if (freshCount == 0) return 0;\n    int minutes = 0;\n    int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};\n    while (!queue.isEmpty() && freshCount > 0) {\n        int size = queue.size();\n        for (int i = 0; i < size; i++) {\n            int[] cell = queue.poll();\n            for (int[] d : DIRS) {\n                int nr = cell[0] + d[0], nc = cell[1] + d[1];\n                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {\n                    grid[nr][nc] = 2;\n                    freshCount--;\n                    queue.offer(new int[]{nr, nc});\n                }\n            }\n        }\n        minutes++;\n    }\n    return freshCount == 0 ? minutes : -1;\n}",
    "curatedProblems": [
      {
        "name": "Rotting Oranges",
        "id": 994,
        "difficulty": "Medium",
        "file": "src/graphs/RottingOranges.java",
        "company": "Google"
      },
      {
        "name": "Number of Islands",
        "id": 200,
        "difficulty": "Medium",
        "file": "src/graphs/NumberOfIslands.java",
        "company": "Google"
      },
      {
        "name": "Course Schedule",
        "id": 207,
        "difficulty": "Medium",
        "file": "src/graphs/CourseSchedule.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Simultaneous shortest path & infection waves ⇒ Seed all sources at t=0, expand in batches",
    "clarifyingQuestions": [
      "Can there be 0 fresh oranges initially? (Return 0 immediately)",
      "Can fresh oranges be completely isolated and unreachable? (Return -1)",
      "What are the grid boundaries? (R, C <= 10)"
    ],
    "interviewTraps": [
      "Running individual BFS from each rotten orange instead of multi-source queue initialization.",
      "Incrementing minute counter even when the wave rot zero fresh cells.",
      "Omitting grid bounds check nr >= 0 && nr < R && nc >= 0 && nc < C."
    ],
    "complexityDeepDive": {
      "time": "O(R · C) - Each grid cell is added and removed from the queue at most once.",
      "space": "O(R · C) - Maximum queue size bounded by perimeter/area of the grid."
    },
    "starterTemplate": "class Solution {\n    public int orangesRotting(int[][] grid) {\n        // TODO: Enqueue all rotten cells at t=0, track freshCount, expand 4-directionally\n        return -1;\n    }\n}"
  },
  {
    "id": "topological-sort",
    "name": "Topological Sort (Kahn's BFS)",
    "category": "Trees & Graphs",
    "badge": "DAG Dependency",
    "overview": "In-degree array + BFS queue ordering directed acyclic graphs to resolve prerequisites and detect cycles.",
    "signalKeywords": [
      "Prerequisites",
      "Course Schedule",
      "Build Order",
      "Dependency Resolution"
    ],
    "invariantProof": "A node with in-degree 0 has all prerequisites fulfilled and can be taken immediately. Removing it decrements neighbor in-degrees, uncovering newly unlocked nodes. If processed node count < total nodes, a cycle exists.",
    "complexity": "Time: O(V + E) · Space: O(V + E)",
    "defaultProblem": "Course Schedule (LeetCode #207)",
    "repoPath": "src/graphs/CourseSchedule.java",
    "repoClass": "graphs.CourseSchedule",
    "presets": [
      {
        "label": "Valid 4 Courses DAG",
        "value": "4; [1,0],[2,0],[3,1],[3,2]"
      },
      {
        "label": "Cycle Detection (2-Loop)",
        "value": "2; [1,0],[0,1]"
      }
    ],
    "codeSnippet": "public boolean canFinish(int numCourses, int[][] prerequisites) {\n    int[] inDegree = new int[numCourses];\n    List<List<Integer>> adj = new ArrayList<>();\n    for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());\n    for (int[] edge : prerequisites) {\n        adj.get(edge[1]).add(edge[0]);\n        inDegree[edge[0]]++;\n    }\n    Queue<Integer> queue = new LinkedList<>();\n    for (int i = 0; i < numCourses; i++) {\n        if (inDegree[i] == 0) queue.offer(i);\n    }\n    int visitedCount = 0;\n    while (!queue.isEmpty()) {\n        int curr = queue.poll();\n        visitedCount++;\n        for (int next : adj.get(curr)) {\n            if (--inDegree[next] == 0) queue.offer(next);\n        }\n    }\n    return visitedCount == numCourses;\n}",
    "curatedProblems": [
      {
        "name": "Course Schedule",
        "id": 207,
        "difficulty": "Medium",
        "file": "src/graphs/CourseSchedule.java",
        "company": "Google"
      },
      {
        "name": "Course Schedule II (Return Order)",
        "id": 210,
        "difficulty": "Medium",
        "file": "src/graphs/CourseSchedule.java",
        "company": "Amazon"
      },
      {
        "name": "Alien Dictionary",
        "id": 269,
        "difficulty": "Hard",
        "file": "src/arrays/AlienDictionary.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Prerequisites & build dependencies ⇒ In-degree array + Queue of 0-prerequisite nodes",
    "clarifyingQuestions": [
      "Can courses have disconnected components? (Yes, Kahn handles disconnected DAGs cleanly)",
      "Can prerequisite pairs contain duplicate edges or self-dependencies?",
      "Is the output a boolean validity check or full topological sequence int[]?"
    ],
    "interviewTraps": [
      "Confusing edge direction: prerequisite [a, b] means b must precede a (edge b -> a, increment inDegree[a]).",
      "Failing cycle detection: if processedCount != numCourses, circular dependency exists.",
      "Not seeding all initial nodes with in-degree 0 into queue before BFS."
    ],
    "complexityDeepDive": {
      "time": "O(V + E) - Visits each course vertex and decrements each prerequisite edge once.",
      "space": "O(V + E) - Stores adjacency graph, in-degree counts, and BFS worklist queue."
    },
    "starterTemplate": "class Solution {\n    public boolean canFinish(int numCourses, int[][] prerequisites) {\n        // TODO: Build graph, inDegree array, queue 0-inDegree roots, verify count\n        return true;\n    }\n}"
  },
  {
    "id": "merge-intervals",
    "name": "Merge Intervals",
    "category": "Arrays & Strings",
    "badge": "Greedy Sweep",
    "overview": "Start-time ordering followed by single-pass contiguous boundary unification.",
    "signalKeywords": [
      "Overlapping Spans",
      "Meeting Schedule",
      "Insert Interval",
      "Time Ranges"
    ],
    "invariantProof": "Once intervals are sorted by start time, any interval that can possibly overlap with interval[i] must have start <= interval[i].end. We either extend the current segment's end or start a new disjoint segment.",
    "complexity": "Time: O(N log N) · Space: O(N)",
    "defaultProblem": "Merge Intervals (LeetCode #56)",
    "repoPath": "src/arrays/MergeIntervals.java",
    "repoClass": "arrays.MergeIntervals",
    "presets": [
      {
        "label": "Standard Intervals",
        "value": "[[1,3],[2,6],[8,10],[15,18]]"
      },
      {
        "label": "Enclosed Intervals",
        "value": "[[1,4],[2,3]]"
      },
      {
        "label": "Chained Overlaps",
        "value": "[[1,4],[4,5],[5,8]]"
      }
    ],
    "codeSnippet": "public int[][] merge(int[][] intervals) {\n    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));\n    List<int[]> merged = new ArrayList<>();\n    for (int[] interval : intervals) {\n        if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {\n            merged.add(interval);\n        } else {\n            merged.get(merged.size() - 1)[1] = \n                Math.max(merged.get(merged.size() - 1)[1], interval[1]);\n        }\n    }\n    return merged.toArray(new int[merged.size()][]);\n}",
    "curatedProblems": [
      {
        "name": "Merge Intervals",
        "id": 56,
        "difficulty": "Medium",
        "file": "src/arrays/MergeIntervals.java",
        "company": "Google"
      },
      {
        "name": "Meeting Rooms",
        "id": 252,
        "difficulty": "Easy",
        "file": "src/arrays/MeetingRooms.java",
        "company": "Google"
      },
      {
        "name": "Insert Interval",
        "id": 57,
        "difficulty": "Medium",
        "file": "src/arrays/InsertInterval.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Overlapping spans ⇒ Sort by start time, then greedy single-pass boundary union",
    "clarifyingQuestions": [
      "Are intervals pre-sorted? (Never assume! Always sort first in O(N log N))",
      "Do adjacent touching boundaries like [1,4] and [4,5] count as overlapping? (Yes, merge to [1,5])",
      "Can intervals have negative coordinates or start > end?"
    ],
    "interviewTraps": [
      "Attempting greedy merge without sorting by start time first.",
      "Overwriting merged end with curr[1] instead of Math.max(prev[1], curr[1]) (fails for enclosed intervals).",
      "Incorrectly indexing dynamic List<int[]> vs primitive 2D arrays."
    ],
    "complexityDeepDive": {
      "time": "O(N log N) - Dominated by initial start-time sorting. Merge scan runs in O(N).",
      "space": "O(N) - Storage for merged intervals list."
    },
    "starterTemplate": "class Solution {\n    public int[][] merge(int[][] intervals) {\n        // TODO: Sort by start time, merge overlaps with Math.max\n        return new int[0][];\n    }\n}"
  },
  {
    "id": "top-k-heap",
    "name": "Top 'K' Elements (Min-Heap)",
    "category": "Stacks & Queues",
    "badge": "Priority Queue",
    "overview": "Maintaining a fixed min-heap of capacity K to track the K largest items in O(N log K).",
    "signalKeywords": [
      "Top K Frequent",
      "Kth Largest Element",
      "K Closest Points",
      "Merge K Sorted"
    ],
    "invariantProof": "A min-heap of size K holds the K largest elements seen so far with the minimum of those K at the root. Any incoming candidate greater than root replaces it, maintaining the K largest invariant without full sorting.",
    "complexity": "Time: O(N log K) · Space: O(K)",
    "defaultProblem": "Top K Frequent Elements (LeetCode #347)",
    "repoPath": "src/arrays/TopKFrequentElements.java",
    "repoClass": "arrays.TopKFrequentElements",
    "presets": [
      {
        "label": "Frequency K=2",
        "value": "[1, 1, 1, 2, 2, 3]; 2"
      },
      {
        "label": "Single Item K=1",
        "value": "[1]; 1"
      },
      {
        "label": "Tie Frequencies K=2",
        "value": "[4, 4, 5, 5, 6]; 2"
      }
    ],
    "codeSnippet": "public int[] topKFrequent(int[] nums, int k) {\n    Map<Integer, Integer> count = new HashMap<>();\n    for (int n : nums) count.put(n, count.getOrDefault(n, 0) + 1);\n\n    PriorityQueue<Integer> heap = new PriorityQueue<>(\n        (a, b) -> count.get(a) - count.get(b) // Min-heap by frequency\n    );\n    for (int n : count.keySet()) {\n        heap.offer(n);\n        if (heap.size() > k) heap.poll(); // Evict smallest\n    }\n    int[] res = new int[k];\n    for (int i = 0; i < k; i++) res[i] = heap.poll();\n    return res;\n}",
    "curatedProblems": [
      {
        "name": "Top K Frequent Elements",
        "id": 347,
        "difficulty": "Medium",
        "file": "src/arrays/TopKFrequentElements.java",
        "company": "Amazon"
      },
      {
        "name": "Kth Largest Element in an Array",
        "id": 215,
        "difficulty": "Medium",
        "file": "src/arrays/TopKFrequentElements.java",
        "company": "Meta"
      },
      {
        "name": "Find Median from Data Stream",
        "id": 295,
        "difficulty": "Hard",
        "file": "src/arrays/TopKFrequentElements.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Top K frequent / Kth largest ⇒ Min-Heap of size K evicts smallest in O(N log K)",
    "clarifyingQuestions": [
      "Is K guaranteed to be <= number of unique elements?",
      "Can elements with identical frequency be returned in any order?",
      "Is an O(N) Bucket Sort solution preferred over O(N log K) Min-Heap?"
    ],
    "interviewTraps": [
      "Using a Max-Heap of size N instead of Min-Heap of size K (wastes memory and runtime).",
      "Sorting entire array in O(N log N) when only top K are requested.",
      "Reversing priority queue comparator: min-heap requires (a, b) -> count.get(a) - count.get(b)."
    ],
    "complexityDeepDive": {
      "time": "O(N log K) - PriorityQueue capped at size K requires log K per insertion/eviction.",
      "space": "O(N + K) - Frequency HashMap takes O(N), Min-Heap takes O(K)."
    },
    "starterTemplate": "class Solution {\n    public int[] topKFrequent(int[] nums, int k) {\n        // TODO: Compute frequency map, maintain Min-Heap of capacity K\n        return new int[k];\n    }\n}"
  },
  {
    "id": "dynamic-programming",
    "name": "0/1 Knapsack & Coin Change DP",
    "category": "Dynamic Programming",
    "badge": "Optimal Substructure",
    "overview": "Tabulating subproblem dependencies to compose optimal solutions without redundant re-evaluation.",
    "signalKeywords": [
      "Fewest Elements to Form Target",
      "Maximum Profit",
      "Climbing Stairs",
      "Subsequence Optimization"
    ],
    "invariantProof": "dp[i] represents the minimum count to form target i using available choices. Since choices only affect future states non-negatively, Bellman's principle of optimality guarantees optimal subproblem composition.",
    "complexity": "Time: O(amount · coins) · Space: O(amount)",
    "defaultProblem": "Coin Change (LeetCode #322)",
    "repoPath": "src/dp/CoinChange.java",
    "repoClass": "dp.CoinChange",
    "presets": [
      {
        "label": "Coins [1,2,5] Target 11",
        "value": "[1, 2, 5]; 11"
      },
      {
        "label": "Coins [2,5] Target 8",
        "value": "[2, 5]; 8"
      },
      {
        "label": "Coins [2] Target 3 (Impossible)",
        "value": "[2]; 3"
      }
    ],
    "codeSnippet": "public int coinChange(int[] coins, int amount) {\n    int[] dp = new int[amount + 1];\n    Arrays.fill(dp, amount + 1);\n    dp[0] = 0;\n    for (int i = 1; i <= amount; i++) {\n        for (int coin : coins) {\n            if (i - coin >= 0 && dp[i - coin] != amount + 1) {\n                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);\n            }\n        }\n    }\n    return dp[amount] > amount ? -1 : dp[amount];\n}",
    "curatedProblems": [
      {
        "name": "Coin Change",
        "id": 322,
        "difficulty": "Medium",
        "file": "src/dp/CoinChange.java",
        "company": "Google"
      },
      {
        "name": "Climbing Stairs",
        "id": 70,
        "difficulty": "Easy",
        "file": "src/dp/ClimbingStairs.java",
        "company": "Amazon"
      },
      {
        "name": "Longest Increasing Subsequence",
        "id": 300,
        "difficulty": "Medium",
        "file": "src/dp/LIS.java",
        "company": "Google"
      }
    ],
    "cheatCode": "Optimal substructure & fewest choices to reach target ⇒ dp[i] = min(dp[i], 1 + dp[i - c])",
    "clarifyingQuestions": [
      "Can target amount be 0? (Return 0 coins immediately)",
      "Can coin denominations be negative or duplicate?",
      "Is coin supply unlimited? (Yes: Unbounded Knapsack / Coin Change)"
    ],
    "interviewTraps": [
      "Using Integer.MAX_VALUE as sentinel causing 32-bit overflow when adding 1. Use amount + 1!",
      "Assuming greedy always works (fails for coins [1, 3, 4] with target 6: greedy gives 4+1+1=3 coins, DP gives 3+3=2 coins).",
      "Flipping loop order in unbounded knapsack (amount must be outer, coins inner)."
    ],
    "complexityDeepDive": {
      "time": "O(amount · |coins|) - Evaluates each subproblem amount against each coin choice.",
      "space": "O(amount) - 1D tabulation array from 0 to amount."
    },
    "starterTemplate": "class Solution {\n    public int coinChange(int[] coins, int amount) {\n        // TODO: Initialize dp[0..amount] to amount + 1, dp[0] = 0, compose bottom-up\n        return -1;\n    }\n}"
  }
];

if (typeof window !== "undefined") {
  window.PATTERNS_DATA = PATTERNS_DATA;
}
if (typeof module !== "undefined" && module.exports) {
  module.exports = { PATTERNS_DATA };
}
