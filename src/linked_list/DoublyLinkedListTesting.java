package linked_list;

import utils.DoublyLinkedList;

public class DoublyLinkedListTesting {
    static void main() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.append(4);
        doublyLinkedList.append(5);
        doublyLinkedList.append(6);
        doublyLinkedList.append(7);
        doublyLinkedList.printList();
        doublyLinkedList.printListReverse();
    }
}
