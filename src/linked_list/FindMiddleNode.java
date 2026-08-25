package linked_list;

import utils.LinkedList;
import utils.Node;

public class FindMiddleNode {

    static void main() {
        System.out.println(findMiddleNode().get_value());
    }

    /// Without counter and Length is Also is not given
    public static Node findMiddleNode() {
        LinkedList linkedList = new LinkedList().addElements(new int[]{1, 2, 3, 5, 6, 7, 8, 9, 0, 11});
        Node temp = linkedList.getHead();
        Node slow = temp;
        Node fast = temp;
        while (fast != null && fast.get_next() != null) {
            fast = fast.get_next().get_next();
            slow = slow.get_next();
        }
        return slow;
    }
}
