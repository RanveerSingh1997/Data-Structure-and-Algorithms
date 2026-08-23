# Java Collections & Syntax Detailed Guide

During an interview, forgetting standard Java syntax is a common pitfall. This guide provides detailed examples of the most commonly used Java Data Structures and methods for DSA.

---

## 1. Arrays (`java.util.Arrays`)
Fixed-size collections.

```java
int[] arr = new int[10];

// 1. Initialize with a specific value (O(N))
Arrays.fill(arr, -1);                  

// 2. Sorting (O(N log N))
Arrays.sort(arr); // Sorts primitive arrays in ascending order

// WARNING: To sort in descending order, you CANNOT use primitive int[].
// You must use the Object wrapper Integer[].
Integer[] objArr = {5, 2, 8, 1};
Arrays.sort(objArr, Collections.reverseOrder()); 

// 3. Copying (O(N))
int[] copy = Arrays.copyOf(arr, arr.length); 
```

---

## 2. Strings & StringBuilder
Strings in Java are **immutable**. Concatenating strings in a loop creates a new String object every time, leading to `O(N^2)` time complexity. Always use `StringBuilder`!

```java
String s = "hello";

// Basic Access
char c = s.charAt(0);                  // Get character at index 0 ('h')
int len = s.length();                  // Length is a method for Strings! (Not a property like in Arrays)

// Conversions
char[] chars = s.toCharArray();        // Convert to char[] to iterate faster or modify
String sub = s.substring(1, 4);        // substring from index 1 to 3 (exclusive 4). Returns "ell"

// String Splitting
String sentence = "a b c";
String[] words = sentence.split(" ");  // ["a", "b", "c"]

// The Proper Way to Build Strings (O(N))
StringBuilder sb = new StringBuilder();
for (char letter : chars) {
    sb.append(letter);
}
sb.reverse();                          // Built-in reverse!
String result = sb.toString();
```

---

## 3. Map (`HashMap` & `TreeMap`)
Extremely important for caching and counting frequencies.

```java
Map<String, Integer> map = new HashMap<>();

// 1. Basic Operations
map.put("A", 1);
map.containsKey("A");                  // O(1) check

// 2. Frequency Counting (The Best Way)
// getOrDefault returns 0 if "B" doesn't exist, preventing NullPointerExceptions
int count = map.getOrDefault("B", 0);  
map.put("B", count + 1);

// 3. putIfAbsent
map.putIfAbsent("C", 5);               // Only puts 5 if "C" is not already in the map

// 4. Iterating through a Map
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
}

// 5. TreeMap (Maintains Keys in Sorted Order - O(log N) operations)
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(10, "Ten");
treeMap.firstKey();                    // Returns the smallest key
treeMap.lastKey();                     // Returns the largest key
```

---

## 4. PriorityQueue (Heaps)
Used for finding the Top K elements, or constantly pulling the minimum/maximum element. By default, Java's `PriorityQueue` is a **Min-Heap**.

```java
// 1. Standard Min-Heap (Smallest element at the top)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// 2. Standard Max-Heap (Largest element at the top)
// Use a lambda comparator to reverse the natural order
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

// 3. Custom Object Heap (e.g., Dijkstra's algorithm storing int[]{node, distance})
// Sort ascending by the distance (the 1st index of the array)
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

// Core Methods
pq.offer(5);                           // Insert element - Time: O(log N)
int min = pq.poll();                   // Remove & Return top element - Time: O(log N)
int top = pq.peek();                   // View top element without removing - Time: O(1)
```

---

## 5. Queue & Deque
For BFS, always use a `Queue`. 

```java
// 1. Standard Queue (FIFO) using LinkedList
Queue<Integer> q = new LinkedList<>();
q.offer(1);                            // Enqueue to the back
q.poll();                              // Dequeue from the front
q.peek();                              // Look at the front element

// NOTE: Use offer() and poll() instead of add() and remove()
// offer() returns false if full, add() throws an Exception.
// poll() returns null if empty, remove() throws an Exception.

// 2. Deque (Double-Ended Queue) using ArrayDeque
// Useful for Sliding Window Maximum problems.
Deque<Integer> dq = new ArrayDeque<>();
dq.offerFirst(1);                      // Add to front
dq.offerLast(2);                       // Add to back
dq.pollFirst();                        // Remove from front
dq.pollLast();                         // Remove from back
```
