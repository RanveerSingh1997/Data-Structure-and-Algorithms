/**
 * ============================================================================
 * Algorithmic Patterns Database & Invariant Matrix
 * ============================================================================
 * Production-ready catalog of core data structure patterns, invariant proofs,
 * testing presets, Big-O complexities, clarifying questions, and interview traps.
 * Enriched with Video Masterclasses, Documentation Hub, and Instant Summaries.
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
    "repoPath": "src/Arrays/ContainerWithMostWater.java",
    "repoClass": "Arrays.ContainerWithMostWater",
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
        "file": "src/Arrays/ContainerWithMostWater.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/UuiTKBwPgAo",
        "docsUrl": "https://neetcode.io/problems/max-water-container",
        "summary": "Two pointers inward; always advance the shorter bottleneck bar."
      },
      {
        "name": "3Sum",
        "id": 15,
        "difficulty": "Medium",
        "file": "src/Arrays/ThreeSum.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/jzZsG8n2R9A",
        "docsUrl": "https://neetcode.io/problems/three-integer-sum",
        "summary": "Sort array, fix one element, run two pointers on remainder skipping duplicates."
      },
      {
        "name": "Two Sum II - Input Array Is Sorted",
        "id": 167,
        "difficulty": "Medium",
        "file": "src/Arrays/TwoSum.java",
        "company": "Amazon",
        "videoUrl": "https://youtu.be/cQ1Oz4ckcMT8",
        "docsUrl": "https://neetcode.io/problems/two-integer-sum-ii",
        "summary": "Sorted input allows L and R pointers to check target sum in O(N) time, O(1) space."
      },
      {
        "name": "Valid Palindrome",
        "id": 125,
        "difficulty": "Easy",
        "file": "src/Strings/ValidPalindrome.java",
        "company": "Meta",
        "videoUrl": "https://youtu.be/jJXJ16kPFWg",
        "docsUrl": "https://neetcode.io/problems/is-palindrome",
        "summary": "Compare alphanumeric characters from opposite ends, skipping punctuation."
      },
      {
        "name": "Trapping Rain Water",
        "id": 42,
        "difficulty": "Hard",
        "file": "src/Arrays/TrappingRainWater.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/ZI2z5pq0TqA",
        "docsUrl": "https://neetcode.io/problems/trapping-rain-water",
        "summary": "Two pointers tracking leftMax and rightMax; advance whichever boundary has smaller max."
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
    "starterTemplate": "class Solution {\n    public int maxArea(int[] height) {\n        int left = 0, right = height.length - 1;\n        int maxArea = 0;\n        while (left < right) {\n            // TODO: Compute container area and advance bottleneck pointer\n        }\n        return maxArea;\n    }\n}",
    "video": {
      "title": "NeetCode - Container With Most Water (LeetCode #11)",
      "channel": "NeetCode",
      "url": "https://youtu.be/UuiTKBwPgAo",
      "embedId": "UuiTKBwPgAo",
      "summary": "Visualizes why moving the taller line can never yield a larger area than the current bottleneck. By always moving the pointer with the shorter bar inward, we test all viable maximum containers in O(N) time without quadratic checking."
    },
    "docs": [
      {
        "title": "NeetCode.io - Container With Most Water",
        "url": "https://neetcode.io/problems/max-water-container",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Container With Most Water",
        "url": "https://takeuforward.org/data-structure/container-with-most-water/",
        "source": "TakeUForward"
      },
      {
        "title": "LeetCode Editorial - Container With Most Water",
        "url": "https://leetcode.com/problems/container-with-most-water/editorial/",
        "source": "LeetCode"
      }
    ],
    "quickSummary": "Initialize two pointers at opposite ends (0 and N-1). Calculate container area as width * min(h[L], h[R]). Since width decreases at every step, the only way to find a larger area is to seek a taller bar; thus, discard the shorter bottleneck pointer and advance inward."
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
    "repoPath": "src/Strings/LongestSubstringWithoutRepeating.java",
    "repoClass": "Strings.LongestSubstringWithoutRepeating",
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
        "file": "src/Strings/LongestSubstringWithoutRepeating.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/wiGpQwVHdE0",
        "docsUrl": "https://neetcode.io/problems/longest-substring-without-duplicates",
        "summary": "Expand right, shrink left when duplicate found in hash set."
      },
      {
        "name": "Minimum Size Subarray Sum",
        "id": 209,
        "difficulty": "Medium",
        "file": "src/Arrays/MinSubArrayLen.java",
        "company": "Meta",
        "videoUrl": "https://youtu.be/aYqYMIJDN40",
        "docsUrl": "https://neetcode.io/problems/minimum-size-subarray-sum",
        "summary": "Expand window adding elements; contract left as long as window sum >= target."
      },
      {
        "name": "Max Consecutive Ones III",
        "id": 1004,
        "difficulty": "Medium",
        "file": "src/Arrays/MaxConsecutiveOnes.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/3E4JBHSLpYk",
        "docsUrl": "https://leetcode.com/problems/max-consecutive-ones-iii/editorial/",
        "summary": "Sliding window tolerating at most K zeros; shrink left when zero count > K."
      },
      {
        "name": "Permutation in String",
        "id": 567,
        "difficulty": "Medium",
        "file": "src/Strings/PermutationInString.java",
        "company": "Microsoft",
        "videoUrl": "https://youtu.be/UbyhOgBN834",
        "docsUrl": "https://neetcode.io/problems/permutation-string",
        "summary": "Fixed window of length |s1| matching 26-char frequency counts."
      },
      {
        "name": "Sliding Window Maximum",
        "id": 239,
        "difficulty": "Hard",
        "file": "src/Stacks_Queues/SlidingWindowMax.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/DfljaUwZsOk",
        "docsUrl": "https://neetcode.io/problems/sliding-window-maximum",
        "summary": "Monotonic decreasing deque holding indices in window; front is always current max."
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
    "starterTemplate": "class Solution {\n    public int lengthOfLongestSubstring(String s) {\n        Map<Character, Integer> lastSeen = new HashMap<>();\n        int left = 0, maxLen = 0;\n        for (int right = 0; right < s.length(); right++) {\n            // TODO: Maintain unique window invariant and track maxLen\n        }\n        return maxLen;\n    }\n}",
    "video": {
      "title": "RisingBrain: Maximum Subarray with Sum K | Brute Force to Optimised (Sliding Window & Prefix Sum)",
      "channel": "RisingBrain (Anjali Kumari)",
      "url": "https://youtu.be/dgjKO46bu3A",
      "embedId": "dgjKO46bu3A",
      "summary": "Comprehensive visual tutorial breaking down transition from O(N^2) brute force to O(N) optimal sliding window and prefix sum hash map. Explains when sliding window is sound (monotonic sum for non-negative values) versus prefix sum hash maps for general arrays."
    },
    "docs": [
      {
        "title": "RisingBrain: Maximum Subarray with Sum K Video Tutorial",
        "url": "https://youtu.be/dgjKO46bu3A",
        "source": "RisingBrain"
      },
      {
        "title": "TakeUForward - Longest Subarray with Sum K",
        "url": "https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/",
        "source": "TakeUForward"
      },
      {
        "title": "RisingBrain DSA Patterns Sheet",
        "url": "https://www.risingbrain.org/sheet",
        "source": "RisingBrain"
      },
      {
        "title": "NeetCode.io - Longest Substring Without Repeating",
        "url": "https://neetcode.io/problems/longest-substring-without-duplicates",
        "source": "NeetCode"
      },
      {
        "title": "LeetCode #560 - Subarray Sum Equals K",
        "url": "https://leetcode.com/problems/subarray-sum-equals-k/",
        "source": "LeetCode"
      }
    ],
    "quickSummary": "Maintain a contiguous range [L..R]. Expand the right boundary to incorporate the next item. When the window violates constraints (e.g. duplicate character, or sum > target for positive numbers), shrink the left boundary until the invariant is restored."
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
    "repoPath": "src/Stacks_Queues/DailyTemperatures.java",
    "repoClass": "Stacks_Queues.DailyTemperatures",
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
        "file": "src/Stacks_Queues/DailyTemperatures.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/cTBiBSnjO3c",
        "docsUrl": "https://neetcode.io/problems/daily-temperatures",
        "summary": "Monotonic decreasing stack storing indices; resolve warmer days on pop."
      },
      {
        "name": "Next Greater Element I",
        "id": 496,
        "difficulty": "Easy",
        "file": "src/Stacks_Queues/NextGreaterElement.java",
        "company": "Amazon",
        "videoUrl": "https://youtu.be/68a1Dc_qVq4",
        "docsUrl": "https://neetcode.io/problems/next-greater-element-i",
        "summary": "Stack finds next greater element for all nums2 items and maps them in a hash map."
      },
      {
        "name": "Largest Rectangle in Histogram",
        "id": 84,
        "difficulty": "Hard",
        "file": "src/Stacks_Queues/LargestRectangleHistogram.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/zx5Sw9x30z0",
        "docsUrl": "https://neetcode.io/problems/largest-rectangle-in-histogram",
        "summary": "Monotonic increasing stack; when bar drops, pop and compute area with popped height."
      },
      {
        "name": "Online Stock Span",
        "id": 901,
        "difficulty": "Medium",
        "file": "src/Stacks_Queues/OnlineStockSpan.java",
        "company": "Meta",
        "videoUrl": "https://youtu.be/slYh0ZNEqSw",
        "docsUrl": "https://leetcode.com/problems/online-stock-span/editorial/",
        "summary": "Stack stores (price, span) pairs; pop and accumulate spans while incoming price >= top."
      },
      {
        "name": "Validate Stack Sequences",
        "id": 946,
        "difficulty": "Medium",
        "file": "src/Stacks_Queues/ValidateStackSequences.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/vHKXMOmgt7w",
        "docsUrl": "https://leetcode.com/problems/validate-stack-sequences/editorial/",
        "summary": "Simulate stack push; greedily pop while stack top matches current popped token."
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
    "starterTemplate": "class Solution {\n    public int[] dailyTemperatures(int[] temperatures) {\n        int n = temperatures.length;\n        int[] ans = new int[n];\n        Deque<Integer> stack = new ArrayDeque<>();\n        for (int i = 0; i < n; i++) {\n            // TODO: Pop cooler indices and record span\n        }\n        return ans;\n    }\n}",
    "video": {
      "title": "NeetCode - Daily Temperatures (LeetCode #739)",
      "channel": "NeetCode",
      "url": "https://youtu.be/cTBiBSnjO3c",
      "embedId": "cTBiBSnjO3c",
      "summary": "Visualizes why keeping unresolved day indices in a monotonic decreasing stack guarantees that when a warmer day arrives, it acts as the immediate next greater element for all smaller days popped from the stack."
    },
    "docs": [
      {
        "title": "NeetCode.io - Daily Temperatures",
        "url": "https://neetcode.io/problems/daily-temperatures",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Next Greater Element",
        "url": "https://takeuforward.org/data-structure/next-greater-element-using-stack/",
        "source": "TakeUForward"
      },
      {
        "title": "LeetCode Editorial - Daily Temperatures",
        "url": "https://leetcode.com/problems/daily-temperatures/editorial/",
        "source": "LeetCode"
      }
    ],
    "quickSummary": "Maintain indices in a stack with values strictly descending. When current temperature is higher than the stack top, pop the top index and record the distance (currIdx - poppedIdx) into the result array. Each index is pushed and popped at most once."
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
    "repoPath": "src/Sorting_Searching/SearchRotatedSortedArray.java",
    "repoClass": "Sorting_Searching.SearchRotatedSortedArray",
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
        "file": "src/Sorting_Searching/SearchRotatedSortedArray.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/U8XENwh8Oy8",
        "docsUrl": "https://neetcode.io/problems/find-target-in-rotated-sorted-array",
        "summary": "Identify the sorted half; if target is within bounds, search there, else search other half."
      },
      {
        "name": "Find Minimum in Rotated Sorted Array",
        "id": 153,
        "difficulty": "Medium",
        "file": "src/Sorting_Searching/FindMinRotated.java",
        "company": "Meta",
        "videoUrl": "https://youtu.be/nIVW4P8b1VA",
        "docsUrl": "https://neetcode.io/problems/find-minimum-in-rotated-sorted-array",
        "summary": "Compare nums[mid] with nums[right] to determine which half contains the inflection point."
      },
      {
        "name": "Find Peak Element",
        "id": 162,
        "difficulty": "Medium",
        "file": "src/Sorting_Searching/FindPeakElement.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/kMzJy9es7Hc",
        "docsUrl": "https://leetcode.com/problems/find-peak-element/editorial/",
        "summary": "Compare nums[mid] with nums[mid+1]; walk uphill towards the peak in O(log N)."
      },
      {
        "name": "Koko Eating Bananas",
        "id": 875,
        "difficulty": "Medium",
        "file": "src/Sorting_Searching/KokoEatingBananas.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/U2SozAs9RzA",
        "docsUrl": "https://neetcode.io/problems/eating-bananas",
        "summary": "Binary search eating speed k in [1, max(piles)]; find minimum feasible speed within h hours."
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
    "starterTemplate": "class Solution {\n    public int search(int[] nums, int target) {\n        int low = 0, high = nums.length - 1;\n        while (low <= high) {\n            int mid = low + (high - low) / 2;\n            // TODO: Identify sorted half and prune\n        }\n        return -1;\n    }\n}",
    "video": {
      "title": "NeetCode - Search in Rotated Sorted Array (LeetCode #33)",
      "channel": "NeetCode",
      "url": "https://youtu.be/U8XENwh8Oy8",
      "embedId": "U8XENwh8Oy8",
      "summary": "Proves that in any rotated sorted array, splitting at midpoint always produces at least one completely sorted subarray. Check if the target is within the sorted boundary; if yes, search there, else search the opposite half."
    },
    "docs": [
      {
        "title": "NeetCode.io - Search Rotated Sorted Array",
        "url": "https://neetcode.io/problems/find-target-in-rotated-sorted-array",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Binary Search in Rotated Array",
        "url": "https://takeuforward.org/data-structure/search-in-rotated-sorted-array/",
        "source": "TakeUForward"
      },
      {
        "title": "CP-Algorithms - Binary Search",
        "url": "https://cp-algorithms.com/num_methods/binary_search.html",
        "source": "CP-Algorithms"
      }
    ],
    "quickSummary": "Calculate midpoint mid = L + (R - L) / 2. Identify which half of the array ([L..mid] or [mid..R]) is sorted by comparing boundary values. If the target lies inside the sorted interval, restrict search to it; otherwise search the disordered half. Halves search range in O(log N)."
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
    "repoPath": "src/LinkedList/FindLoop.java",
    "repoClass": "LinkedList.FindLoop",
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
        "file": "src/LinkedList/FindLoop.java",
        "company": "Amazon",
        "videoUrl": "https://youtu.be/gBTe7lFR3vc",
        "docsUrl": "https://neetcode.io/problems/linked-list-cycle-detection",
        "summary": "Slow moves 1, fast moves 2; collision indicates cycle in O(1) space."
      },
      {
        "name": "Middle of the Linked List",
        "id": 876,
        "difficulty": "Easy",
        "file": "src/LinkedList/FindMiddleNode.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/A2_ldqM4QcY",
        "docsUrl": "https://neetcode.io/problems/middle-of-the-linked-list",
        "summary": "When fast pointer reaches list end, slow pointer rests precisely on the median node."
      },
      {
        "name": "Linked List Cycle II (Find Start)",
        "id": 142,
        "difficulty": "Medium",
        "file": "src/LinkedList/FindLoop.java",
        "company": "Meta",
        "videoUrl": "https://youtu.be/wjYnzkAhcNk",
        "docsUrl": "https://takeuforward.org/data-structure/find-the-starting-point-of-the-loop-in-a-linked-list/",
        "summary": "After collision, reset one pointer to head and move both at speed 1 to find cycle entrance."
      },
      {
        "name": "Happy Number",
        "id": 202,
        "difficulty": "Easy",
        "file": "src/LinkedList/FindLoop.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/ljz85bxOYJ0",
        "docsUrl": "https://neetcode.io/problems/non-cyclical-number",
        "summary": "Treat sum of squared digits as next pointer; detect infinite loop with Floyd's cycle detection."
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
    "starterTemplate": "public class Solution {\n    public boolean hasCycle(ListNode head) {\n        if (head == null || head.next == null) return false;\n        ListNode slow = head, fast = head;\n        while (fast != null && fast.next != null) {\n            // TODO: Advance slow 1x and fast 2x\n        }\n        return false;\n    }\n}",
    "video": {
      "title": "NeetCode - Linked List Cycle Detection (LeetCode #141)",
      "channel": "NeetCode",
      "url": "https://youtu.be/gBTe7lFR3vc",
      "embedId": "gBTe7lFR3vc",
      "summary": "Floyd's Tortoise and Hare algorithm: slow pointer moves 1 node, fast pointer moves 2 nodes. Proves that if a cycle exists, the relative gap between them decreases by 1 on every step until collision, operating in O(1) auxiliary space."
    },
    "docs": [
      {
        "title": "NeetCode.io - Linked List Cycle Detection",
        "url": "https://neetcode.io/problems/linked-list-cycle-detection",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Detect Cycle in Linked List",
        "url": "https://takeuforward.org/data-structure/detect-a-cycle-in-a-linked-list/",
        "source": "TakeUForward"
      }
    ],
    "quickSummary": "Move slow pointer by 1 step and fast pointer by 2 steps. If fast or fast.next reaches null, the list is acyclic. If slow and fast point to the same node, a cycle exists. To find the midpoint of a list, when fast hits the end, slow is precisely at the median node."
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
    "repoPath": "src/Trees/InvertBinaryTree.java",
    "repoClass": "Trees.InvertBinaryTree",
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
        "file": "src/Trees/InvertBinaryTree.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/OnSn2XEQ4MY",
        "docsUrl": "https://neetcode.io/problems/invert-a-binary-tree",
        "summary": "Swap left and right children, then recursively invert subtrees."
      },
      {
        "name": "Diameter of Binary Tree",
        "id": 543,
        "difficulty": "Easy",
        "file": "src/Trees/DiameterOfBinaryTree.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/bkxqA8Rfv04",
        "docsUrl": "https://neetcode.io/problems/binary-tree-diameter",
        "summary": "Bottom-up DFS returns subtree height while updating global diameter = leftHeight + rightHeight."
      },
      {
        "name": "Validate Binary Search Tree",
        "id": 98,
        "difficulty": "Medium",
        "file": "src/Trees/ValidateBinarySearchTree.java",
        "company": "Amazon",
        "videoUrl": "https://youtu.be/s6ATEkipzow",
        "docsUrl": "https://neetcode.io/problems/valid-binary-search-tree",
        "summary": "Validate BST invariant by passing allowable range (minVal, maxVal) down the recursion."
      },
      {
        "name": "Lowest Common Ancestor",
        "id": 236,
        "difficulty": "Medium",
        "file": "src/Trees/LowestCommonAncestor.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/gs2LMfuOR9k",
        "docsUrl": "https://neetcode.io/problems/lowest-common-ancestor-in-binary-search-tree",
        "summary": "Bottom-up DFS: if both left and right return non-null, current node is the LCA."
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
    "starterTemplate": "class Solution {\n    public TreeNode invertTree(TreeNode root) {\n        if (root == null) return null;\n        // TODO: Swap subtrees recursively\n        return root;\n    }\n}",
    "video": {
      "title": "NeetCode - Invert Binary Tree (LeetCode #226)",
      "channel": "NeetCode",
      "url": "https://youtu.be/OnSn2XEQ4MY",
      "embedId": "OnSn2XEQ4MY",
      "summary": "Explains depth-first recursive subtree swapping: swap left and right child pointers at the current node, then recursively invert the left and right subtrees until base null is reached."
    },
    "docs": [
      {
        "title": "NeetCode.io - Invert Binary Tree",
        "url": "https://neetcode.io/problems/invert-a-binary-tree",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Invert Binary Tree",
        "url": "https://takeuforward.org/data-structure/invert-a-binary-tree/",
        "source": "TakeUForward"
      },
      {
        "title": "GeeksforGeeks - Binary Tree Traversals",
        "url": "https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/",
        "source": "GeeksforGeeks"
      }
    ],
    "quickSummary": "Traverse tree nodes recursively (pre-order, in-order, or post-order). Base case: if root is null, return null. Swap child pointers, recurse left, recurse right, and bubble subtree solutions up to the caller."
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
    "repoPath": "src/Graphs/RottingOranges.java",
    "repoClass": "Graphs.RottingOranges",
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
        "file": "src/Graphs/RottingOranges.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/y704cgRxPQA",
        "docsUrl": "https://neetcode.io/problems/rotting-fruit",
        "summary": "Enqueue all initial rotten oranges; expand 4-directionally level by level until no fresh remain."
      },
      {
        "name": "Number of Islands",
        "id": 200,
        "difficulty": "Medium",
        "file": "src/Graphs/NumberOfIslands.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/pV2kpPD66nE",
        "docsUrl": "https://neetcode.io/problems/count-number-of-islands",
        "summary": "Iterate grid; finding '1' increments island count and triggers BFS/DFS sinking connected land."
      },
      {
        "name": "Course Schedule",
        "id": 207,
        "difficulty": "Medium",
        "file": "src/Graphs/CourseSchedule.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/EgI5nU9etnU",
        "docsUrl": "https://neetcode.io/problems/course-schedule",
        "summary": "Directed graph cycle detection using Kahn's BFS with in-degree array."
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
    "starterTemplate": "class Solution {\n    public int orangesRotting(int[][] grid) {\n        // TODO: Enqueue all rotten cells at t=0, track freshCount, expand 4-directionally\n        return -1;\n    }\n}",
    "video": {
      "title": "NeetCode - Rotting Oranges (LeetCode #994)",
      "channel": "NeetCode",
      "url": "https://youtu.be/y704cgRxPQA",
      "embedId": "y704cgRxPQA",
      "summary": "Multi-source BFS using a queue. All initially rotten oranges are added at minute 0. The queue expands outward simultaneously in 4 directions, rotting adjacent fresh oranges minute-by-minute until all reachable oranges are processed."
    },
    "docs": [
      {
        "title": "NeetCode.io - Rotting Fruit",
        "url": "https://neetcode.io/problems/rotting-fruit",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Rotting Oranges Multi-source BFS",
        "url": "https://takeuforward.org/data-structure/rotting-oranges-min-time-to-rot-all-oranges-bfs/",
        "source": "TakeUForward"
      },
      {
        "title": "LeetCode Editorial - Rotting Oranges",
        "url": "https://leetcode.com/problems/rotting-oranges/editorial/",
        "source": "LeetCode"
      }
    ],
    "quickSummary": "Seed a queue with all starting nodes (e.g. all rotten oranges). Process queue level-by-level using queue.size(). For each node, inspect 4-directional cardinal neighbors; mutate state and enqueue valid neighbors. Guarantees shortest time / path in uniform grids."
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
    "repoPath": "src/Graphs/CourseSchedule.java",
    "repoClass": "Graphs.CourseSchedule",
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
        "file": "src/Graphs/CourseSchedule.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/EgI5nU9etnU",
        "docsUrl": "https://neetcode.io/problems/course-schedule",
        "summary": "Kahn's BFS in-degrees; cycle detected if visited vertex count < total vertices."
      },
      {
        "name": "Course Schedule II (Return Order)",
        "id": 210,
        "difficulty": "Medium",
        "file": "src/Graphs/CourseSchedule.java",
        "company": "Amazon",
        "videoUrl": "https://youtu.be/Akt3glAwyfY",
        "docsUrl": "https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/",
        "summary": "Return array of topological order from Kahn's BFS, or empty array if cycle detected."
      },
      {
        "name": "Alien Dictionary",
        "id": 269,
        "difficulty": "Hard",
        "file": "src/Arrays/AlienDictionary.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/6kTZYvNNyps",
        "docsUrl": "https://neetcode.io/problems/foreign-dictionary",
        "summary": "Build directed graph from adjacent dictionary words; run topological sort for alphabet order."
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
    "starterTemplate": "class Solution {\n    public boolean canFinish(int numCourses, int[][] prerequisites) {\n        // TODO: Build graph, inDegree array, queue 0-inDegree roots, verify count\n        return true;\n    }\n}",
    "video": {
      "title": "NeetCode - Course Schedule (LeetCode #207)",
      "channel": "NeetCode",
      "url": "https://youtu.be/EgI5nU9etnU",
      "embedId": "EgI5nU9etnU",
      "summary": "Kahn's algorithm for topological sorting: compute in-degrees for all vertices. Enqueue nodes with in-degree 0. As each course is completed, decrement prerequisite counts for dependent neighbors. If processed courses count equals total vertices, no cycle exists."
    },
    "docs": [
      {
        "title": "NeetCode.io - Course Schedule",
        "url": "https://neetcode.io/problems/course-schedule",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Topological Sort (Kahn's Algorithm)",
        "url": "https://takeuforward.org/data-structure/topological-sort-bfs/",
        "source": "TakeUForward"
      },
      {
        "title": "CP-Algorithms - Topological Sorting",
        "url": "https://cp-algorithms.com/graph/topological-sort.html",
        "source": "CP-Algorithms"
      }
    ],
    "quickSummary": "Calculate the in-degree (number of incoming dependency edges) for every vertex. Enqueue all 0-in-degree nodes. Repeatedly pop a node, append to topological order, and decrement neighbor in-degrees. If neighbor hits 0, enqueue it. If output length < V, graph has a cycle."
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
    "repoPath": "src/Arrays/MergeIntervals.java",
    "repoClass": "Arrays.MergeIntervals",
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
        "file": "src/Arrays/MergeIntervals.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/44H3cEC2fFM",
        "docsUrl": "https://neetcode.io/problems/merge-intervals",
        "summary": "Sort by start times; merge when current interval start <= previous interval end."
      },
      {
        "name": "Meeting Rooms",
        "id": 252,
        "difficulty": "Easy",
        "file": "src/Arrays/MeetingRooms.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/PaJxqZVPhbg",
        "docsUrl": "https://neetcode.io/problems/meeting-schedule",
        "summary": "Sort by start; return false if any adjacent intervals overlap (intervals[i][0] < intervals[i-1][1])."
      },
      {
        "name": "Insert Interval",
        "id": 57,
        "difficulty": "Medium",
        "file": "src/Arrays/InsertInterval.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/A8NUOmlwOlM",
        "docsUrl": "https://neetcode.io/problems/insert-new-interval",
        "summary": "Add all intervals ending before newInterval starts; merge overlapping ones; append remaining."
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
    "starterTemplate": "class Solution {\n    public int[][] merge(int[][] intervals) {\n        // TODO: Sort by start time, merge overlaps with Math.max\n        return new int[0][];\n    }\n}",
    "video": {
      "title": "NeetCode - Merge Intervals (LeetCode #56)",
      "channel": "NeetCode",
      "url": "https://youtu.be/44H3cEC2fFM",
      "embedId": "44H3cEC2fFM",
      "summary": "Sort intervals primarily by start time. Iterate through sorted intervals: if the current start time is <= the end time of the previous interval, merge them by extending the previous interval end to max(prev.end, curr.end); otherwise create a new interval."
    },
    "docs": [
      {
        "title": "NeetCode.io - Merge Intervals",
        "url": "https://neetcode.io/problems/merge-intervals",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Merge Overlapping Sub-intervals",
        "url": "https://takeuforward.org/data-structure/merge-overlapping-sub-intervals/",
        "source": "TakeUForward"
      },
      {
        "title": "GeeksforGeeks - Merge Overlapping Intervals",
        "url": "https://www.geeksforgeeks.org/merging-intervals/",
        "source": "GeeksforGeeks"
      }
    ],
    "quickSummary": "Sort intervals by start time. Initialize result list with first interval. For each subsequent interval: if curr[0] <= prev[1], overlap occurs: update prev[1] = Math.max(prev[1], curr[1]). If no overlap, add curr interval to result list."
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
    "repoPath": "src/Arrays/TopKFrequentElements.java",
    "repoClass": "Arrays.TopKFrequentElements",
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
        "file": "src/Arrays/TopKFrequentElements.java",
        "company": "Amazon",
        "videoUrl": "https://youtu.be/YPTqKIgVk-k",
        "docsUrl": "https://neetcode.io/problems/top-k-elements-in-list",
        "summary": "Frequency hash map with bucket sort where index = frequency yields O(N) time."
      },
      {
        "name": "Kth Largest Element in an Array",
        "id": 215,
        "difficulty": "Medium",
        "file": "src/Arrays/TopKFrequentElements.java",
        "company": "Meta",
        "videoUrl": "https://youtu.be/XEmy13g1Qxc",
        "docsUrl": "https://neetcode.io/problems/kth-largest-element-in-an-array",
        "summary": "Min-Heap of size K retains top elements; peek() yields Kth largest in O(N log K)."
      },
      {
        "name": "Find Median from Data Stream",
        "id": 295,
        "difficulty": "Hard",
        "file": "src/Arrays/TopKFrequentElements.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/itmhHWaHupI",
        "docsUrl": "https://neetcode.io/problems/find-median-in-a-data-stream",
        "summary": "Two heaps (Max-Heap for lower half, Min-Heap for upper half) maintain median in O(1) peek."
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
    "starterTemplate": "class Solution {\n    public int[] topKFrequent(int[] nums, int k) {\n        // TODO: Compute frequency map, maintain Min-Heap of capacity K\n        return new int[k];\n    }\n}",
    "video": {
      "title": "NeetCode - Top K Frequent Elements (LeetCode #347)",
      "channel": "NeetCode",
      "url": "https://youtu.be/YPTqKIgVk-k",
      "embedId": "YPTqKIgVk-k",
      "summary": "Contrasts O(N log K) min-heap with O(N) bucket sort. Frequency count is stored in map. An array of buckets where index represents frequency allows collecting the top K elements in a single reverse sweep."
    },
    "docs": [
      {
        "title": "NeetCode.io - Top K Elements in List",
        "url": "https://neetcode.io/problems/top-k-elements-in-list",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Top K Frequent Elements",
        "url": "https://takeuforward.org/arrays/top-k-frequent-elements/",
        "source": "TakeUForward"
      },
      {
        "title": "Abdul Bari - Heap Data Structure",
        "url": "https://youtu.be/HqPJF2L5h9U",
        "source": "Abdul Bari"
      }
    ],
    "quickSummary": "Count occurrences in a hash map. For O(N) optimal time, create N+1 frequency buckets and append values into bucket[count]. Scan buckets backwards from N down to 0, accumulating values until K items are gathered."
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
    "repoPath": "src/DP/CoinChange.java",
    "repoClass": "DP.CoinChange",
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
        "file": "src/DP/CoinChange.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/H9bfqozJoqs",
        "docsUrl": "https://neetcode.io/problems/coin-change",
        "summary": "Bottom-up 1D DP: dp[a] = min(dp[a], 1 + dp[a - coin]) for each coin."
      },
      {
        "name": "Climbing Stairs",
        "id": 70,
        "difficulty": "Easy",
        "file": "src/DP/ClimbingStairs.java",
        "company": "Amazon",
        "videoUrl": "https://youtu.be/Y0lT9Fck7qI",
        "docsUrl": "https://neetcode.io/problems/climbing-stairs",
        "summary": "Fibonacci recurrence dp[i] = dp[i-1] + dp[i-2] solved with two variables in O(1) space."
      },
      {
        "name": "Longest Increasing Subsequence",
        "id": 300,
        "difficulty": "Medium",
        "file": "src/DP/LIS.java",
        "company": "Google",
        "videoUrl": "https://youtu.be/cjWnW0hdF1Y",
        "docsUrl": "https://neetcode.io/problems/longest-increasing-subsequence",
        "summary": "Patience sorting / binary search maintains tails array in O(N log N) time."
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
    "starterTemplate": "class Solution {\n    public int coinChange(int[] coins, int amount) {\n        // TODO: Initialize dp[0..amount] to amount + 1, dp[0] = 0, compose bottom-up\n        return -1;\n    }\n}",
    "video": {
      "title": "NeetCode - Coin Change (LeetCode #322)",
      "channel": "NeetCode",
      "url": "https://youtu.be/H9bfqozJoqs",
      "embedId": "H9bfqozJoqs",
      "summary": "Bottom-up tabulation: initialize dp array of size amount + 1 with amount + 1. Set dp[0] = 0. For each coin and each target amount from coin to total, update dp[a] = min(dp[a], 1 + dp[a - coin])."
    },
    "docs": [
      {
        "title": "NeetCode.io - Coin Change",
        "url": "https://neetcode.io/problems/coin-change",
        "source": "NeetCode"
      },
      {
        "title": "TakeUForward - Coin Change 2 DP",
        "url": "https://takeuforward.org/data-structure/coin-change-2-dp-22/",
        "source": "TakeUForward"
      },
      {
        "title": "Abdul Bari - Dynamic Programming Principles",
        "url": "https://youtu.be/oBt53YbR9Kk",
        "source": "Abdul Bari"
      }
    ],
    "quickSummary": "Bottom-up array dp[0..amount] where dp[i] represents min coins to make amount i. Initialize entries to sentinel (amount + 1), with dp[0] = 0. For each subproblem amount i from 1 to target and each coin c <= i: dp[i] = min(dp[i], 1 + dp[i - c]). Return dp[amount] > amount ? -1 : dp[amount]."
  }
];

if (typeof window !== "undefined") {
  window.PATTERNS_DATA = PATTERNS_DATA;
}
if (typeof module !== "undefined" && module.exports) {
  module.exports = { PATTERNS_DATA };
}
