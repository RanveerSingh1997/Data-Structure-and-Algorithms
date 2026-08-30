package utils;

public class DoublyLinkedList {
    int length;
    Node head;
    Node tail;


    public DoublyLinkedList() {
    }

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length++;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.get_value() + " ");
            temp = temp.get_next();
        }
        System.out.println();
    }

    public void printListReverse() {
        Node temp = tail;
        while (temp != null) {
            System.out.print(temp.get_value() + " ");
            temp = temp.get_prev();
        }
        System.out.println();
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.set_next(newNode);
            newNode.set_prev(tail);
            tail = newNode;
        }
        length++;
    }

    public void prepand(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.set_next(head);
            head.set_prev(newNode);
            head = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.get_prev();
            tail.set_next(null);
            temp.set_prev(null);
        }
        length--;
        return temp;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.get_next();
            head.set_prev(null);
            temp.set_next(null);
        }
        length--;
        return temp;
    }


    public Node get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node temp = head;
        if (index < length / 2) {
            for (int i = 0; i < index; i++) {
                temp = temp.get_next();
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.get_prev();
            }
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.set_value(value);
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepand(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.get_next();
        newNode.set_next(after);
        newNode.set_prev(before);
        before.set_next(newNode);
        after.set_prev(newNode);
        length++;
        return false;
    }


    public Node remove(int index) {
        if (index < 0 || index > length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        /// temp.next.prev = temp.prev;
        /// temp.prev.next = temp.next;
        /// temp.next =null;
        /// temp.prev =null;
        Node temp = get(index);
        temp.get_prev().get_next().set_next(temp.get_next());
        temp.get_next().get_prev().set_next(temp.get_prev());
        length--;
        return temp;
    }
}
