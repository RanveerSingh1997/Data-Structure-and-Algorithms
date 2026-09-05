package stacks_queues;

import utils.Queue;

public class StackWithQueue {
    Queue queue1;
    Queue queue2;

    StackWithQueue() {
        queue1 = new Queue();
        queue2 = new Queue();
    }

    static void main() {
        StackWithQueue stackWithQueue = new StackWithQueue();
        stackWithQueue.push(1);
        stackWithQueue.push(2);
        stackWithQueue.push(3);
        stackWithQueue.push(4);
        stackWithQueue.push(5);
        System.out.println(stackWithQueue.pop());
        System.out.println(stackWithQueue.pop());
        System.out.println(stackWithQueue.pop());
        System.out.println(stackWithQueue.pop());
        System.out.println(stackWithQueue.top());
        System.out.println(stackWithQueue.isEmpty());
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
        return queue1.getLast();
    }

}
