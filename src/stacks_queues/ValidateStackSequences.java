package stacks_queues;

/**
 * ============================================================================
 * Problem: Validate Stack Sequences
 * LeetCode #946 | Difficulty: Medium
 * Company: Google, Amazon, Bloomberg Interview Question
 * Link: https://leetcode.com/problems/validate-stack-sequences/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given two integer arrays `pushed` and `popped` each with distinct values, return
 * `true` if this could have been the result of a sequence of push and pop operations
 * on an initially empty stack, or `false` otherwise.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: pushed = [1, 2, 3, 4, 5], popped = [4, 5, 3, 2, 1]
 *   Output: true
 *   Explanation: We might do the following operations:
 *     push(1), push(2), push(3), push(4),
 *     pop() -> 4,
 *     push(5),
 *     pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1.
 *     All elements popped in the desired order!
 *
 * Example 2:
 *   Input: pushed = [1, 2, 3, 4, 5], popped = [4, 3, 5, 1, 2]
 *   Output: false
 *   Explanation: 1 cannot be popped before 2.
 *     After pushing 1, 2, 3, 4 and popping 4, 3:
 *     Next popped is 5, so push 5, pop 5.
 *     Now top of stack is 2, but the sequence expects 1 next! Impossible.
 *
 * ⚙️ CONSTRAINTS:
 *  - 1 <= pushed.length <= 1000
 *  - 0 <= pushed[i] <= 1000
 *  - All the elements of pushed are unique.
 *  - popped.length == pushed.length
 *  - popped is a permutation of pushed.
 *
 * 💡 INTERVIEW HINTS:
 *  - Can you simulate the push and pop operations greedily?
 *  - Iterate through elements in `pushed` and push each one onto a Stack.
 *  - After each push, check: is the stack non-empty AND does `stack.peek() == popped[popIndex]`?
 *    While that condition holds, pop from the stack and advance `popIndex++`.
 *  - After processing all pushed elements, what should the state of the stack be?
 *    (It should be completely empty, or `popIndex == popped.length`).
 *  - Follow-up: Can you do this in O(1) auxiliary space by using `pushed` array itself as the stack?
 */
public class ValidateStackSequences {

    /**
     * Determines whether the given pushed and popped sequences are valid.
     *
     * @param pushed sequence of values pushed onto stack
     * @param popped expected sequence of popped values
     * @return true if sequence is valid; false otherwise
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // TODO: Implement your solution here
        return false;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        ValidateStackSequences solver = new ValidateStackSequences();

        System.out.println("=== Testing: LeetCode 946 - Validate Stack Sequences ===");

        // Test 1: [1,2,3,4,5], [4,5,3,2,1] -> true
        int[] pushed1 = {1, 2, 3, 4, 5};
        int[] popped1 = {4, 5, 3, 2, 1};
        boolean res1 = solver.validateStackSequences(pushed1, popped1);
        System.out.println("Test 1 Result: " + res1 + " | Expected: true");
        if (res1) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [1,2,3,4,5], [4,3,5,1,2] -> false
        int[] pushed2 = {1, 2, 3, 4, 5};
        int[] popped2 = {4, 3, 5, 1, 2};
        boolean res2 = solver.validateStackSequences(pushed2, popped2);
        System.out.println("Test 2 Result: " + res2 + " | Expected: false");
        if (!res2) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Single element matching [1], [1] -> true
        int[] pushed3 = {1};
        int[] popped3 = {1};
        boolean res3 = solver.validateStackSequences(pushed3, popped3);
        System.out.println("Test 3 Result: " + res3 + " | Expected: true");
        if (res3) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }

        // Test 4: Two elements reversed [1, 2], [2, 1] -> true
        int[] pushed4 = {1, 2};
        int[] popped4 = {2, 1};
        boolean res4 = solver.validateStackSequences(pushed4, popped4);
        System.out.println("Test 4 Result: " + res4 + " | Expected: true");
        if (res4) {
            System.out.println("  [PASS] Test 4");
        } else {
            System.out.println("  [TODO] Test 4 not passing yet");
        }
    }
}
