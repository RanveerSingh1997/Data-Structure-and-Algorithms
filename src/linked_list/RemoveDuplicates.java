package linked_list;

import utils.LinkedList;
import utils.Node;

import java.util.HashSet;
import java.util.Set;


/**
 * Problem: Remove Duplicates from an Unsorted Linked List
 * <p>
 * Approach 1 (Hash Set): O(N) Time, O(N) Space
 * Approach 2 (Nested Loops): O(N^2) Time, O(1) Space
 */
public class RemoveDuplicates {

    // Approach 2: Without extra buffer (two loops)
    public Node removeDuplicatesWithLoop(Node head) {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.get_next() != null) {
                if (runner.get_next().get_value() == current.get_value()) {
                    runner.set_next(runner.get_next().get_next());
                } else {
                    runner = runner.get_next();
                }
            }
            current = current.get_next();
        }
        return head;
    }

    // Approach 1: Using HashSet
    public Node removeDuplicates(Node head) {
        if (head == null) return null;
        Set<Integer> visited = new HashSet<>();
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (visited.contains(current.get_value())) {
                previous.set_next(current.get_next());
            } else {
                visited.add(current.get_value());
                previous = current;
            }
            current = current.get_next();
        }
        return head;
    }

    public void removeDuplicates(LinkedList list) {
        if (list != null && list.getHead() != null) {
            removeDuplicates(list.getHead());
        }
    }

    public static void main(String[] args) {
        RemoveDuplicates solver = new RemoveDuplicates();

        // Helper to print linked nodes
        java.util.function.Consumer<Node> printNodeList = (h) -> {
            Node curr = h;
            while (curr != null) {
                System.out.print(curr.get_value() + (curr.get_next() != null ? " -> " : ""));
                curr = curr.get_next();
            }
            System.out.println();
        };

        // Test 1: Using HashSet
        Node n1 = new Node(1, new Node(2, new Node(2, new Node(3, new Node(1, null)))));
        System.out.println("=== Testing RemoveDuplicates ===");
        System.out.print("Original list: ");
        printNodeList.accept(n1);

        solver.removeDuplicates(n1);
        System.out.print("After removeDuplicates (HashSet): ");
        printNodeList.accept(n1);

        // Test 2: Using Nested Loops (no extra buffer)
        Node n2 = new Node(4, new Node(5, new Node(4, new Node(6, new Node(5, null)))));
        System.out.print("\nOriginal list 2: ");
        printNodeList.accept(n2);

        solver.removeDuplicatesWithLoop(n2);
        System.out.print("After removeDuplicatesWithLoop:   ");
        printNodeList.accept(n2);
    }
}
