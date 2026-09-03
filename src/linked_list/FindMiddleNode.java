package linked_list;

import utils.LinkedList;
import utils.Node;

/**
 * Problem: Middle of the Linked List
 * Link: <a href="https://leetcode.com/problems/middle-of-the-linked-list/">...</a>
 * <p>
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class FindMiddleNode {

    public static Node findMiddleNode(Node head) {
        if (head == null) return null;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.get_next() != null) {
            fast = fast.get_next().get_next();
            slow = slow.get_next();
        }
        return slow;
    }

    public static Node findMiddleNode(LinkedList list) {
        if (list == null) return null;
        return findMiddleNode(list.getHead());
    }

    /// Without counter and Length is Also is not given
    public static Node findMiddleNode() {
        LinkedList linkedList = new LinkedList().addElements(new int[]{1, 2, 3, 5, 6, 7, 8, 9, 0, 11});
        return findMiddleNode(linkedList.getHead());
    }

    static void main(String[] args) {
        Node middle = findMiddleNode();
        if (middle != null) {
            System.out.println("Middle node value: " + middle.get_value());
        }
    }
}
