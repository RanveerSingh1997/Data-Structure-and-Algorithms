package linked_list;


import utils.LinkedList;
import utils.Node;

/**
 * Problem: Find Kth Element From End of Linked List
 * <p>
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class FindKthElementFromEnd {
    public Node findKthElement(Node head, int k) {
        if (head == null || k <= 0) return null;
        Node fast = head;
        Node slow = head;
        for (int i = 0; i < k; i++) {
            if (fast != null) {
                fast = fast.get_next();
            } else {
                return null;
            }
        }
        while (fast != null) {
            fast = fast.get_next();
            slow = slow.get_next();
        }
        return slow;
    }

    public Node findKthElement(LinkedList list, int k) {
        if (list == null) return null;
        return findKthElement(list.getHead(), k);
    }

    public Node findKthElement(int k) {
        return findKthElement(new LinkedList().getHead(), k);
    }
}
