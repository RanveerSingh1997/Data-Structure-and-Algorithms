package utils;

public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    public LinkedList() {
    }


    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.get_value());
            temp = temp.get_next();
        }
    }

    public Node getHead() {
        if (head == null) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.get_value());
        }
        return head;
    }

    public void getTail() {
        if (tail == null) {
            System.out.println("Tail: null");
        } else {
            System.out.println("Tail: " + tail.get_value());
        }
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        } else {
            tail.set_next(newNode);
        }
        tail = newNode;
        length++;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while (temp.get_next() != null) {
            pre = temp;
            temp = temp.get_next();
        }
        tail = pre;
        tail.set_next(null);
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void prepand(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.set_next(head);
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.get_next();
        temp.set_next(null);
        length--;
        if (length == 0) {
            tail = null;
        }
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index > length) {
            return null;
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.get_next();
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
        Node temp = get(index - 1);
        newNode.set_next(temp.get_next());
        temp.set_next(newNode);
        length++;
        return false;
    }

    public Node remove(int index) {
        if (index < 0 || index > length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();
        Node prev = get(index - 1);
        Node temp = prev.get_next();
        prev.set_next(temp.get_next());
        temp.set_next(null);
        length--;
        return temp;
    }

    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node after = temp.get_next();
        Node before = null;
        for (int i = 0; i < length; i++) {
            after = temp.get_next();
            temp.set_next(before);
            before = temp;
            temp = after;
        }
    }

    public LinkedList addElements(int[] elements) {
        LinkedList linkedList = new LinkedList(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            linkedList.append(elements[i]);
            length++;
        }
        return linkedList;
    }
}
