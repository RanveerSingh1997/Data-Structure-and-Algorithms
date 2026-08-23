package utils;

/**
 * Standard Linked List Node used in most LeetCode problems.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}
    
    public ListNode(int val) { 
        this.val = val; 
    }
    
    public ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
    
    // Helper method to print the linked list
    public void printList() {
        ListNode current = this;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
