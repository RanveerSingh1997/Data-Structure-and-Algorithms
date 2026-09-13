package Heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private final List<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public static void main(String[] args) {

        MinHeap myHeap = new MinHeap();
        myHeap.insert(99);
        myHeap.insert(72);
        myHeap.insert(61);
        myHeap.insert(58);
        myHeap.insert(10);
        myHeap.insert(75);

        System.out.println(myHeap.getHeap());

        Integer removedValue1 = myHeap.remove();

        System.out.println("First Removed Value: " + removedValue1);
        System.out.println(myHeap.getHeap());

        Integer removedValue2 = myHeap.remove();

        System.out.println("Second Removed Value: " + removedValue2);
        System.out.println(myHeap.getHeap());

        /*
            EXPECTED OUTPUT:
            ----------------
            [10, 58, 72, 99, 61, 75]
            First Removed Value: 10
            [58, 61, 72, 99, 75]
            Second Removed Value: 58
            [61, 75, 72, 99]
        */

    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    public int leftChild(int index) {
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value) {
        heap.add(value);
        int current = heap.size() - 1;
        while (current >= 0 && heap.get(current) < heap.get(parent(current))) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Integer remove() {
        if (heap.isEmpty()) {
            return null;
        }
        int current = heap.getFirst();
        if (heap.size() == 1) {
            heap.removeFirst();
            return current;
        }

        int last = heap.removeLast();
        heap.set(0, last);
        sinkDown(0);
        return current;
    }

    public void sinkDown(int index) {
        int minIndex = index;
        while (true) {
            int leftChild = leftChild(index);
            int rightChild = rightChild(index);
            if (leftChild < heap.size() && heap.get(leftChild) < heap.get(minIndex)) {
                minIndex = leftChild;
            }
            if (rightChild < heap.size() && heap.get(rightChild) < heap.get(minIndex)) {
                minIndex = rightChild;
            }
            if (minIndex != index) {
                swap(minIndex, index);
                index = minIndex;
            } else {
                return;
            }
        }
    }

}
