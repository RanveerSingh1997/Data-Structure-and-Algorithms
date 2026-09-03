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
}
