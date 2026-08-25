package utils;

public class Node {
    Node _next;
    int _value;
    Node _prev;

    Node(int value) {
        this._value = value;
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
}
