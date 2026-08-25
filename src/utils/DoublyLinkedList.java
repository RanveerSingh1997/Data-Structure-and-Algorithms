package utils;

public class DoublyLinkedList {
    int length;
    Node head;
    Node tail;


    DoublyLinkedList() {
    }

    DoublyLinkedList(int value) {
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

    public void append(int value) {

    }

    public void prepand(int value) {

    }
}
