# 🗺️ Hashing, HashSet & Prefix Sum Mastery Guide

Hashing is one of the most versatile and high-yield algorithmic techniques in technical interviews. It allows you to reduce time complexity from $O(N^2)$ to $O(N)$ or $O(1)$ by trading space for constant-time lookup.

---

## 1. Core Intuition & The Space-Time Tradeoff

Whenever a brute-force solution uses nested loops to search for a match or check a condition, ask:
> *"Can I record what I have seen so far in a hash structure so future elements can look it up in $O(1)$ time?"*

```
Brute Force:  For each element X -> Scan the rest of the array for Y -> O(N^2)
With Hashing: For each element X -> Check if Y is in the Hash Table  -> O(N)
```

### Array as Hash Table vs. `HashMap<Character, Integer>`
When the keyspace is small and bounded (e.g., ASCII characters, lowercase English letters `'a'` - `'z'`), **prefer a fixed-size primitive array** over Java Collections:

```java
// Lowercase English letters only:
int[] freq = new int[26];
freq[c - 'a']++;

// Full ASCII (128 chars) or Extended ASCII (256 chars):
int[] asciiFreq = new int[128];
asciiFreq[c]++;
```

**Why prefer primitive arrays when possible?**
1. **Zero Autoboxing**: `HashMap<Character, Integer>` boxes primitives into `Character` and `Integer` objects on the heap, triggering garbage collection and cache misses.
2. **True $O(1)$ Constant Time**: No hash functions, bucket chains, or tree traversals; direct memory offset lookup.
3. **Guaranteed $O(1)$ Space**: Exactly 26 or 128 integers allocated once.

---

## 2. The 5 Essential Hashing Archetypes

### Pattern 1: Complement Lookup (History Matching)
* **When to use**: Finding a pair of elements that satisfy an equation (e.g., $X + Y = \text{Target} \implies Y = \text{Target} - X$).
* **Core Invariant**: When inspecting element at index $i$, the hash structure contains all processed elements from indices $0 \dots i-1$.
* **Lookup Formula**: `complement = target - current`

#### Visual Walkthrough
```
Array: [2, 7, 11, 15], Target: 9

Step 0: Map = {}
Step 1: num = 2  -> complement = 9 - 2 = 7. 7 in Map? NO  -> Map.put(2, index:0)
Step 2: num = 7  -> complement = 9 - 7 = 2. 2 in Map? YES -> Match found! Pair: [Map.get(2), 1] -> [0, 1]
```

#### Generic Template
```java
public int[] findComplementPair(int[] nums, int target) {
    Map<Integer, Integer> seen = new HashMap<>(); // Stores value -> index
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (seen.containsKey(complement)) {
            return new int[]{seen.get(complement), i};
        }
        seen.put(nums[i], i);
    }
    return new int[]{};
}
```

---

### Pattern 2: Prefix Sum + HashMap (Subarray Equivalence)
* **When to use**: Questions asking for a contiguous subarray whose sum equals $K$, is divisible by $K$, or has an equal number of 0s and 1s.
* **The Fundamental Math**:
  Let $\text{prefixSum}[i]$ be the cumulative sum from index $0$ to $i$.
  $$\text{sum}(i \dots j) = \text{prefixSum}[j] - \text{prefixSum}[i - 1]$$
  We want $\text{sum}(i \dots j) = K$:
  $$\text{prefixSum}[j] - \text{prefixSum}[i - 1] = K \iff \text{prefixSum}[i - 1] = \text{prefixSum}[j] - K$$

```
Index:         0     1     2     3     4
Array:       [ 1,    2,    3,    4,    5 ]
PrefixSum:     1     3     6    10    15

Subarray [1..3] sum = (2 + 3 + 4) = 9
Using formula: PrefixSum[3] - PrefixSum[0] = 10 - 1 = 9  ✓
```

#### The Essential `map.put(0, -1)` or `map.put(0, 1)` Setup
> [!IMPORTANT]
> **Why do we initialize `map.put(0, ...)` before the loop?**
> If a valid subarray begins at index `0` and sums to $K$, then:
> $$\text{currentPrefixSum} - K = 0$$
> If `0` is not in our map, we will fail to recognize that the subarray starting from the very beginning of the array matches the target!
> - For **Finding Subarray Indices**: `map.put(0, -1)` because a subarray starting at index `0` has an imaginary left boundary at `-1` (length $= i - (-1) = i + 1$).
> - For **Counting Subarrays**: `map.put(0, 1)` because there is initially 1 empty prefix with sum 0.

#### Generic Template: Subarray Target Indices
```java
public int[] subarraySumIndices(int[] nums, int target) {
    Map<Integer, Integer> prefixToIndex = new HashMap<>();
    prefixToIndex.put(0, -1); // Handles subarrays starting at index 0
    
    int currentSum = 0;
    for (int i = 0; i < nums.length; i++) {
        currentSum += nums[i];
        int requiredPrefix = currentSum - target;
        
        if (prefixToIndex.containsKey(requiredPrefix)) {
            // Subarray runs from (previous index + 1) up to i
            return new int[]{prefixToIndex.get(requiredPrefix) + 1, i};
        }
        
        // Use putIfAbsent if you want the LONGEST subarray (keeps earliest index)
        prefixToIndex.putIfAbsent(currentSum, i);
    }
    return new int[]{};
}
```

---

### Pattern 3: Canonical Key / Equivalence Signature
* **When to use**: Grouping or matching elements that have equivalent characteristics despite different surface representations (e.g., Anagrams, Rotated strings, Isomorphic structures).
* **The Strategy**: Design a deterministic transformation function $f(\text{item}) \to \text{Key}$ such that two items belong to the same group if and only if $f(A) == f(B)$.

#### Key Generation Strategies for Strings:
1. **Sorted Characters**: Good for short words ($K \le 100$).
   ```java
   char[] chars = s.toCharArray();
   Arrays.sort(chars);
   String key = new String(chars); // "eat" -> "aet", "tea" -> "aet"
   ```
2. **Frequency Tuple / Signature String**: Good for long strings ($K > 10,000$) to avoid $O(K \log K)$ sorting:
   ```java
   int[] count = new int[26];
   for (char c : s.toCharArray()) count[c - 'a']++;
   StringBuilder sb = new StringBuilder();
   for (int i = 0; i < 26; i++) {
       sb.append('#').append(count[i]); // e.g., "#1#0#0...#1"
   }
   String key = sb.toString();
   ```

#### Generic Template
```java
public List<List<String>> groupEquivalenceClasses(String[] items) {
    Map<String, List<String>> groups = new HashMap<>();
    for (String item : items) {
        String key = generateSignature(item);
        groups.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
    }
    return new ArrayList<>(groups.values());
}
```

---

### Pattern 4: Set-Based Sequence Traversal ($O(N)$ Streak Finding)
* **When to use**: Finding consecutive sequences in an unsorted array in $O(N)$ time without sorting.
* **The Insight**: A number $X$ is the **start of a streak** if and only if $X - 1$ is NOT in the set!
  - If $X - 1$ exists, skip $X$ because it will be processed as part of an earlier streak.
  - This ensures every element is visited at most twice (once in the outer loop, once in the streak expansion), achieving strict $O(N)$ time.

```
Array: [100, 4, 200, 1, 3, 2]
Set:   {1, 2, 3, 4, 100, 200}

Number 100: Is 99 in Set?  NO  -> Head of streak! Count: 100 (len 1)
Number 4:   Is 3 in Set?   YES -> Not a head, SKIP!
Number 200: Is 199 in Set? NO  -> Head of streak! Count: 200 (len 1)
Number 1:   Is 0 in Set?   NO  -> Head of streak! Count: 1 -> 2 -> 3 -> 4 (len 4)
Number 3:   Is 2 in Set?   YES -> Not a head, SKIP!
Number 2:   Is 1 in Set?   YES -> Not a head, SKIP!

Max Streak = 4
```

#### Generic Template
```java
public int longestConsecutiveStreak(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) set.add(num);
    
    int longest = 0;
    for (int num : set) { // Iterate over SET, not array, to skip duplicate work
        if (!set.contains(num - 1)) { // Head of sequence
            int current = num;
            int streak = 1;
            while (set.contains(current + 1)) {
                current++;
                streak++;
            }
            longest = Math.max(longest, streak);
        }
    }
    return longest;
}
```

---

### Pattern 5: Frequency Map & Bucket Sort
* **When to use**: Finding top $K$ frequent elements, first non-repeating element, or validating frequency thresholds in $O(N)$ time.
* **The Insight**: A standard `PriorityQueue` takes $O(N \log K)$. But frequencies are integers bounded by the array length ($0 \dots N$). We can use an array of lists (`List<Integer>[] buckets`) indexed by frequency to achieve true $O(N)$ time!

```
Array: [1, 1, 1, 2, 2, 3]  (Length N = 6)
Frequencies: 1 -> 3, 2 -> 2, 3 -> 1

Buckets (Index = Frequency):
Index 0: []
Index 1: [3]
Index 2: [2]
Index 3: [1]
Index 4..6: []

Scan backwards from bucket N down to 0 to collect Top K elements!
```

---

## 3. Java Under the Hood & Interview Traps

### The `equals()` and `hashCode()` Contract
If you use a custom object as a `HashMap` key or `HashSet` element:
1. If `o1.equals(o2) == true`, then `o1.hashCode() == o2.hashCode()` **MUST** be true.
2. If two objects have different hash codes, they can never be equal.
3. If you override `equals()`, you **must** override `hashCode()`. If you fail to do so, two distinct objects with identical fields will hash to different buckets, making lookups return `null`.

### Hash Collision Resolution in Java 8+
- Java’s `HashMap` uses separate chaining with buckets.
- **Degradation**: If many keys hash to the same bucket, the linked list chain grows to $O(N)$ search time.
- **Treeification**: In Java 8+, when a single bucket exceeds `TREEIFY_THRESHOLD = 8` entries and the map capacity is $\ge 64$, Java converts that bucket from a linked list into a balanced Red-Black Tree (`TreeNode`), ensuring worst-case $O(\log N)$ lookup per bucket.

### `HashMap` vs `LinkedHashMap` vs `TreeMap`
| Map Type | Underlying Structure | Key Ordering | `get` / `put` Time | Use Case |
| :--- | :--- | :--- | :--- | :--- |
| **`HashMap`** | Hash Table + Red-Black Tree | None (arbitrary) | $O(1)$ amortized | Default choice for caching & counting |
| **`LinkedHashMap`** | Hash Table + Doubly-Linked List | Insertion Order or Access Order | $O(1)$ amortized | Implementing LRU Cache, predictable ordering |
| **`TreeMap`** | Red-Black Tree | Sorted Natural / Comparator | $O(\log N)$ | Range queries, `floorKey()`, `ceilingKey()` |
