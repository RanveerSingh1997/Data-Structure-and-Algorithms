package linked_list;


import utils.LinkedList;
import utils.Node;

/**
 * Problem: Linked List Cycle Detection (Floyd's Tortoise and Hare)
 * 
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class FindLoop {
    public boolean hasLoop(Node head) {
        if (head == null) return false;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.get_next() != null) {
            slow = slow.get_next();
            fast = fast.get_next().get_next();
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLoop(LinkedList list) {
        if (list == null) return false;
        return hasLoop(list.getHead());
    }

    public boolean hasLoop() {
        LinkedList linkedList = new LinkedList().addElements(new int[]{2, 4, 5, 6, 7, 8, 9, 12, 13, 14, 15});
        return hasLoop(linkedList);
    }
}
