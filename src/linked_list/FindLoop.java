package linked_list;


import utils.LinkedList;
import utils.Node;

public class FindLoop {
    public boolean hasLoop() {
        LinkedList linkedList = new LinkedList().addElements(new int[]{2, 4, 5, 6, 7, 8, 9, 12, 13, 14, 15});
        Node temp = linkedList.getHead();
        Node slow = temp;
        Node fast = temp;
        while (fast != null && fast.get_next() != null) {
            slow = slow.get_next();
            fast = fast.get_next().get_next();
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
