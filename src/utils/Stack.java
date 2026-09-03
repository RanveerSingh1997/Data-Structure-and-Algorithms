package utils;

public class Stack {
    private Node top;
    private int height;

    public Stack() {}

    public Stack(int value) {
        top = new Node(value);
        height = 1;
    }

    public void printStack() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getTop() {
        if (top == null) {
            System.out.println("TOP: null");
        } else {
            System.out.println("TOP: " + top.value);
        }
    }

    public void getHeight() {
        System.out.println("Height: " + height);
    }

    public int height() {
        return height;
    }

    public boolean isEmpty() {
        return height == 0;
    }

    public Integer peek() {
        return top == null ? null : top.value;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        if (height != 0) {
            newNode.next = top;
        }
        top = newNode;
        height++;
    }

    public Node pop() {
        if (height == 0) return null;
        Node temp = top;
        top = top.next;
        temp.next = null;
        height--;
        return temp;
    }

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }
}
