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

    public static void main(String[] args) {
        FindKthElementFromEnd solver = new FindKthElementFromEnd();
        LinkedList list = new LinkedList();
        list.addElements(new int[]{10, 20, 30, 40, 50});

        System.out.println("=== Testing FindKthElementFromEnd ===");
        System.out.print("List: ");
        list.printList();

        Node k1 = solver.findKthElement(list, 1);
        System.out.println("k=1 (1st from end) -> Value: " + (k1 != null ? k1.get_value() : "null") + " (Expected 50)");

        Node k3 = solver.findKthElement(list, 3);
        System.out.println("k=3 (3rd from end) -> Value: " + (k3 != null ? k3.get_value() : "null") + " (Expected 30)");

        Node k5 = solver.findKthElement(list, 5);
        System.out.println("k=5 (5th from end) -> Value: " + (k5 != null ? k5.get_value() : "null") + " (Expected 10)");

        Node k10 = solver.findKthElement(list, 10);
        System.out.println("k=10 (out of bounds)-> Value: " + (k10 != null ? k10.get_value() : "null") + " (Expected null)");
    }
}
