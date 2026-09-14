package Heap;

import java.util.PriorityQueue;

public class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Case 1");
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));  // 4
        System.out.println(kthLargest.add(5));  // 5
        System.out.println(kthLargest.add(10)); // 5
        System.out.println(kthLargest.add(9));  // 8
        System.out.println(kthLargest.add(4));

        System.out.println("Test Case 2");
        KthLargest kthLargest2 = new KthLargest(1, new int[]{});
        System.out.println(kthLargest2.add(-3)); // -3
        System.out.println(kthLargest2.add(-2)); // -2
        System.out.println(kthLargest2.add(-4)); // -2
        System.out.println(kthLargest2.add(0));  // 0
        System.out.println(kthLargest2.add(4));  // 4

        System.out.println("Test Case 3");
        KthLargest kthLargest3 = new KthLargest(2, new int[]{5, -1, 2});
        System.out.println(kthLargest3.add(3)); // 3
        System.out.println(kthLargest3.add(6)); // 5
        System.out.println(kthLargest3.add(4)); // 5
    }

    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
        return heap.peek();
    }

}
