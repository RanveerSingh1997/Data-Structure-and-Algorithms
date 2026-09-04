package utils;

public class Queue {
    private Node first;
    private Node last;
    private int length;

    public Queue() {
    }

    public Queue(int value) {
        Node newNode = new Node(value);
        first = newNode;
        last = newNode;
        length = 1;
    }

    public void printFirst() {
        if (first == null) {
            System.out.println("FIRST: null");
        } else {
            System.out.println("FIRST: " + first._value);
        }
    }

    public int getLast() {
        if (last == null) {
            System.out.println("LAST: null");
        } else {
            System.out.println("LAST: " + last._value);
        }
        assert last != null;
        return last._value;
    }

    public void getLength() {
        System.out.println("LENGTH: " + length);
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            first = newNode;
        } else {
            last._next = newNode;
        }
        last = newNode;
        length++;
    }

    public Node dequeue() {
        if (length == 0) return null;
        Node temp = first;
        if (length == 1) {
            first = null;
            last = null;
        } else {
            first = first._next;
            temp._next = null;
        }
        length--;
        return temp;
    }
}
