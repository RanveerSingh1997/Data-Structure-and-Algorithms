package Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        System.out.println(lastStoneWeight.lastStoneWeight(new int[]{2, 3, 6, 2, 4}));
        System.out.println(lastStoneWeight.lastStoneWeight(new int[]{1, 2}));
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : stones) {
            heap.offer(num);
        }
        while (heap.size() > 1) {
            int max1 = heap.poll();
            int max2 = heap.poll();
            if (max1 != max2) {
                heap.offer(max1 - max2);
            }
        }
        return heap.isEmpty() ? 0 : heap.peek();
    }
}
