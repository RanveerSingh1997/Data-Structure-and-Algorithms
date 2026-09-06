package stacks_queues;

/**
 * ============================================================================
 * Problem: Implement Queue using Stacks
 * LeetCode #232 | Difficulty: Easy
 * Link: https://leetcode.com/problems/implement-queue-using-stacks/
 * ============================================================================
 * <p>
 * 📖 PROBLEM DESCRIPTION:
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue
 * should support all the functions of a normal queue (push, peek, pop, and empty).
 * <p>
 * Implement the MyQueue class:
 * - void push(int x) Pushes element x to the back of the queue.
 * - int pop() Removes the element from the front of the queue and returns it.
 * - int peek() Returns the element at the front of the queue.
 * - boolean empty() Returns true if the queue is empty, false otherwise.
 * <p>
 * 📥 EXAMPLES:
 * MyQueue queue = new MyQueue();
 * queue.push(1); // queue is: [1]
 * queue.push(2); // queue is: [1, 2] (leftmost is front)
 * queue.peek();  // return 1
 * queue.pop();   // return 1, queue is [2]
 * queue.empty(); // return false
 * <p>
 * ⚙️ CONSTRAINTS:
 * - 1 <= x <= 9
 * - At most 100 calls will be made to push, pop, peek, and empty.
 * - All calls to pop and peek are valid.
 * <p>
 * ⏱️ COMPLEXITY:
 * - Push-costly approach (current): Push is O(N), Pop/Peek is O(1).
 * - Amortized Pop-costly approach: Push is O(1), Pop/Peek is Amortized O(1).
 */
public class QueueWithStack {
    private final StackTemplate<Integer> stack1;
    private final StackTemplate<Integer> stack2;

    public QueueWithStack() {
        stack1 = new StackTemplate<>();
        stack2 = new StackTemplate<>();
    }

    static void main(String[] args) {
        QueueWithStack queueWithStack = new QueueWithStack();
        System.out.println("=== Testing: LeetCode 232 - Queue With Stack ===");
        queueWithStack.enqueue(1);
        queueWithStack.enqueue(2);
        queueWithStack.enqueue(3);
        queueWithStack.enqueue(4);
        queueWithStack.enqueue(5);
        System.out.print("Queue contents: ");
        queueWithStack.printQueue();
        System.out.println("Peek: " + queueWithStack.peek() + " (Expected: 1)");
        System.out.println("Dequeue: " + queueWithStack.dequeue() + " (Expected: 1)");
        System.out.println("Peek after dequeue: " + queueWithStack.peek() + " (Expected: 2)");
    }

    public void enqueue(int value) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(value);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public Integer dequeue() {
        if (stack1.isEmpty()) {
            return null;
        }
        return stack1.pop();
    }

    public void printQueue() {
        stack1.printStack();
    }

    public int peek() {
        return stack1.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }
}
