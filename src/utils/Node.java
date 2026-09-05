package utils;

public class Node {
    Node _next;
    int _value;
    Node _prev;

    public Node(int value) {
        this._value = value;
    }

    public Node(int value, Node next) {
        this._value = value;
        this._next = next;
    }

    public Node() {
    }

    // Helper: create a linked list from an array
    public static Node fromArray(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Node dummy = new Node(0);
        Node curr = dummy;
        for (int val : arr) {
            curr.set_next(new Node(val));
            curr = curr.get_next();
        }
        return dummy.get_next();
    }

    public int get_value() {
        return _value;
    }

    public void set_value(int _value) {
        this._value = _value;
    }

    public Node get_next() {
        return _next;
    }

    public void set_next(Node _next) {
        this._next = _next;
    }

    public Node get_prev() {
        return _prev;
    }

    public void set_prev(Node _prev) {
        this._prev = _prev;
    }

    // Helper: convert linked list to string representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = this;
        while (curr != null) {
            sb.append(curr.get_value());
            if (curr.get_next() != null) sb.append(" -> ");
            curr = curr.get_next();
        }
        return sb.toString();
    }
}
