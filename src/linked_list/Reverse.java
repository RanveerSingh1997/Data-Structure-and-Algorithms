package linked_list;

import utils.Node;
import utils.ListNode;

/**
 * Problem: Reverse a Singly Linked List
 * Link: https://leetcode.com/problems/reverse-linked-list/
 * 
 * Iterative Approach: O(N) Time, O(1) Space
 * Recursive Approach: O(N) Time, O(N) Space (call stack)
 */
public class Reverse {

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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
