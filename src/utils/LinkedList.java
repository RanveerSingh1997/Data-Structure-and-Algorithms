package utils;


class SolutionTesting {
    static void main() {
        LinkedList linkedList = new LinkedList(3);
        /// Append Function for adding new nodes
        System.out.println("Adding new element");
        linkedList.append(4);
        linkedList.append(6);
        linkedList.printList();
        /// remove last node function calls
        System.out.println("Remove all Elements from list");
        System.out.println(linkedList.removeLast().value);
        System.out.println(linkedList.removeLast().value);
        System.out.println(linkedList.removeLast().value);
        System.out.println(linkedList.removeLast());
        linkedList.printList();
        /// prepand fucntion call to add new node at first place
        System.out.println("Adding new Elements Initial: ");
        linkedList.prepand(81);
        linkedList.prepand(43);
        linkedList.printList();
        System.out.println("Remove First Element: ");
        linkedList.removeFirst();
        linkedList.printList();
    }
}


public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getHead() {
        if (head == null) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.value);
        }
    }

    public void getTail() {
        if (tail == null) {
            System.out.println("Tail: null");
        } else {
            System.out.println("Tail: " + tail.value);
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
            tail.next = newNode;
        }
        tail = newNode;
        length++;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
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
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
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
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
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
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return false;
    }

    public Node remove(int index){
        if(index<0 || index>length) return  null;
        if(index==0) return removeFirst();
        if(index == length-1) return removeLast();
        Node prev =get(index-1);
        Node temp = prev.next;
        prev.next = temp.next;
        temp.next =null;
        length--;
        return temp;
    }

}
