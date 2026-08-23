# Common DSA Patterns Cheat Sheet

When you look at a problem, you should be able to guess the pattern based on the input and what is being asked.

## 1. Sliding Window
- **When to use**: The problem asks for the maximum/minimum/longest/shortest **subarray** or **substring** (contiguous elements) that satisfy a condition.
- **Example**: "Longest Substring Without Repeating Characters"

## 2. Two Pointers
- **When to use**: 
  - The input array is **sorted** and you need to find a pair of elements that fulfill a constraint.
  - You need to compare elements from the start and end of an array.
- **Example**: "Two Sum II", "Container With Most Water", "Valid Palindrome"

## 3. Fast & Slow Pointers (Tortoise and Hare)
- **When to use**: You need to find a cycle in a linked list, or find the middle of a linked list.
- **Example**: "Linked List Cycle", "Middle of the Linked List"

## 4. Merge Intervals
- **When to use**: The problem involves overlapping intervals or scheduling.
- **Example**: "Merge Intervals", "Insert Interval"

## 5. Cyclic Sort
- **When to use**: You are given an array of numbers in the range `1` to `n` (or `0` to `n`) and you need to find the missing/duplicate number.
- **Example**: "Find the Missing Number", "Find All Duplicates in an Array"

## 6. Monotonic Stack
- **When to use**: You need to find the "Next Greater Element" or "Next Smaller Element" for every element in an array.
- **Example**: "Daily Temperatures", "Largest Rectangle in Histogram"

## 7. Top K Elements (Heaps)
- **When to use**: You need to find the top/smallest/frequent `K` elements of a collection.
- **Example**: "Kth Largest Element in an Array", "Top K Frequent Elements"

## 8. Modified Binary Search
- **When to use**: The array is sorted, but modified (e.g., rotated), or you are searching for a condition rather than an exact match.
- **Example**: "Search in Rotated Sorted Array", "Find Minimum in Rotated Sorted Array", "Koko Eating Bananas"

## 9. Backtracking
- **When to use**: You need to find **all possible combinations/permutations** of elements.
- **Example**: "Subsets", "Permutations", "Combination Sum"

## 10. Dynamic Programming
- **When to use**: 
  - The problem asks for the maximum/minimum ways to do something, or if it is possible.
  - The problem can be broken down into overlapping subproblems.
  - **Keywords**: "Max ways", "Min cost", "Can we do it?".
- **Example**: "Climbing Stairs", "Coin Change", "Longest Common Subsequence"
