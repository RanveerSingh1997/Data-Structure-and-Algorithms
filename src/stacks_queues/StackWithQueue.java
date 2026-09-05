package stacks_queues;

import utils.Queue;

/**
 * ============================================================================
 * Problem: Implement Stack using Queues
 * LeetCode #225 | Difficulty: Easy
 * Link: https://leetcode.com/problems/implement-stack-using-queues/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack
 * should support all the functions of a normal stack (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 *   - void push(int x) Pushes element x to the top of the stack.
 *   - int pop() Removes the element on the top of the stack and returns it.
 *   - int top() Returns the element on the top of the stack.
 *   - boolean empty() Returns true if the stack is empty, false otherwise.
 *
 * 📥 EXAMPLES:
 *   MyStack stack = new MyStack();
 *   stack.push(1);
 *   stack.push(2);
 *   stack.top();   // returns 2
 *   stack.pop();   // returns 2
 *   stack.empty(); // returns false
 *
 * ⚙️ CONSTRAINTS:
 *   - 1 <= x <= 9
 *   - At most 100 calls will be made to push, pop, top, and empty.
 *   - All calls to pop and top are valid.
 *
 * ⏱️ COMPLEXITY:
 *   - Push: O(N) (re-enqueueing into new queue).
 *   - Pop / Top / Empty: O(1).
 */
public class StackWithQueue {
    Queue queue1;
    Queue queue2;

    public StackWithQueue() {
        queue1 = new Queue();
        queue2 = new Queue();
    }

    public static void main(String[] args) {
        StackWithQueue stackWithQueue = new StackWithQueue();
        System.out.println("=== Testing: LeetCode 225 - Stack With Queue ===");
        stackWithQueue.push(1);
        stackWithQueue.push(2);
        stackWithQueue.push(3);
        stackWithQueue.push(4);
        stackWithQueue.push(5);
        System.out.println("Pop: " + stackWithQueue.pop() + " (Expected: 5)");
        System.out.println("Pop: " + stackWithQueue.pop() + " (Expected: 4)");
        System.out.println("Top: " + stackWithQueue.top() + " (Expected: 3)");
        System.out.println("Empty: " + stackWithQueue.isEmpty() + " (Expected: false)");
    }

    public void push(int x) {
        queue2.enqueue(x);
        while (!queue1.isEmpty()) {
            queue2.enqueue(queue1.dequeue().get_value());
        }
        queue1 = queue2;
        queue2 = new Queue();
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public int pop() {
        return queue1.dequeue().get_value();
    }

    public int top() {
        return queue1.getFirst();
    }
}
