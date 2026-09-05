package linked_list;

import utils.Node;

/**
 * Problem: Reverse a Singly Linked List
 * Link: <a href="https://leetcode.com/problems/reverse-linked-list/">...</a>
 * <p>
 * Iterative Approach: O(N) Time, O(1) Space
 * Recursive Approach: O(N) Time, O(N) Space (call stack)
 */
public class Reverse {

    static void main(String[] args) {
        Reverse solver = new Reverse();

        System.out.println("=== Testing Reverse Linked List ===");

        // Test with standard ListNode
        Node list1 = Node.fromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original ListNode: " + list1);
        Node rev1 = solver.reverseList(list1);
        System.out.println("Reversed ListNode: " + rev1 + " (Expected 5 -> 4 -> 3 -> 2 -> 1)");

        // Test with Node (Iterative)
        Node n1 = new Node(10, new Node(20, new Node(30, null)));
        Node revIter = solver.reverseIterative(n1);
        System.out.print("\nIterative reversed Node: ");
        Node curr = revIter;
        while (curr != null) {
            System.out.print(curr.get_value() + (curr.get_next() != null ? " -> " : ""));
            curr = curr.get_next();
        }
        System.out.println(" (Expected 30 -> 20 -> 10)");

        // Test with Node (Recursive)
        Node n2 = new Node(100, new Node(200, new Node(300, null)));
        Node revRec = solver.reverseRecursive(n2);
        System.out.print("Recursive reversed Node: ");
        curr = revRec;
        while (curr != null) {
            System.out.print(curr.get_value() + (curr.get_next() != null ? " -> " : ""));
            curr = curr.get_next();
        }
        System.out.println(" (Expected 300 -> 200 -> 100)");
    }

    // 1. Iterative reversal for utils.Node
    public Node reverseIterative(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.get_next();
            curr.set_next(prev);
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    // 2. Recursive reversal for utils.Node
    public Node reverseRecursive(Node head) {
        if (head == null || head.get_next() == null) {
            return head;
        }
        Node newHead = reverseRecursive(head.get_next());
        head.get_next().set_next(head);
        head.set_next(null);
        return newHead;
    }

    // 3. LeetCode-standard signature using utils.ListNode
    public Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.get_next();
            curr.set_next(prev);
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
