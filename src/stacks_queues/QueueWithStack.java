package stacks_queues;


import utils.StackTemplate;

public class QueueWithStack {
    private final StackTemplate<Integer> stack1;
    private final StackTemplate<Integer> stack2;


    QueueWithStack() {
        stack1 = new StackTemplate<>();
        stack2 = new StackTemplate<>();
    }

    static void main() {
        QueueWithStack queueWithStack = new QueueWithStack();
        queueWithStack.enqueue(1);
        queueWithStack.enqueue(2);
        queueWithStack.enqueue(3);
        queueWithStack.enqueue(4);
        queueWithStack.enqueue(5);
        queueWithStack.printQueue();

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
