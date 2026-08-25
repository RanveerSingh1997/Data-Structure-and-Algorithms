package utils;

public class DoublyLinkedList {
    int length;
    Node head;
    Node tail;


    public DoublyLinkedList() {
    }

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length++;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.get_value() + " ");
            temp = temp.get_next();
        }
        System.out.println();
    }

    public void printListReverse() {
        Node temp = tail;
        while (temp != null) {
            System.out.print(temp.get_value() + " ");
            temp = temp.get_prev();
        }
        System.out.println();
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.set_next(newNode);
            newNode.set_prev(tail);
            tail = newNode;
        }
        length++;
    }

    public void prepand(int value) {

    }
}
