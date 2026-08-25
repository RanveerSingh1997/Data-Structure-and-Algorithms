package linked_list;

import utils.LinkedList;
import utils.Node;

import java.util.HashSet;
import java.util.Set;


public class RemoveDuplicates {

    public void removeDuplicatesWithLoop() {

    }


    public void removeDuplicates() {
        Set<Integer> visited = new HashSet<>();
        Node current = new LinkedList().getHead();
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
    }
}
