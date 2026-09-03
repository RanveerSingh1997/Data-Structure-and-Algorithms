package linked_list;

import utils.DoublyLinkedList;

public class DoublyLinkedListTesting {
    public static void main(String[] args) {
        System.out.println("=== Testing DoublyLinkedList ===");
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.append(4);
        doublyLinkedList.append(5);
        doublyLinkedList.append(6);
        doublyLinkedList.append(7);
        System.out.print("Forward list:  ");
        doublyLinkedList.printList();
        System.out.print("Reverse list:  ");
        doublyLinkedList.printListReverse();

        System.out.println("\nInserting 99 at index 2 (between 5 and 6):");
        doublyLinkedList.insert(2, 99);
        doublyLinkedList.printList();

        System.out.println("\nRemoving element at index 2 (value 99):");
        doublyLinkedList.remove(2);
        doublyLinkedList.printList();
    }
}
