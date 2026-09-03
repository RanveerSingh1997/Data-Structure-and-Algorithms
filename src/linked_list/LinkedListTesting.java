package linked_list;

import utils.LinkedList;

public class LinkedListTesting {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList(5);
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.printList();
        linkedList.prepand(5);
        linkedList.prepand(6);
        linkedList.prepand(11);
        linkedList.prepand(7);
        linkedList.prepand(8);
        linkedList.prepand(9);
        linkedList.printList();
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.reverse();
        System.out.println(linkedList.getHead());
        System.out.println(linkedList.getTail());
        System.out.println(linkedList.getLength());
        linkedList.remove(4);
        linkedList.set(4, 5);
        linkedList.remove(4);
        linkedList.printList();
    }
}
