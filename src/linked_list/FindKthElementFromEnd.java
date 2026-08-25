package linked_list;


import utils.LinkedList;
import utils.Node;

public class FindKthElementFromEnd {
    public Node findKthElement(int k) {
        Node temp = new LinkedList().getHead();
        Node fast = temp;
        Node slow = temp;
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
}
