package Heap;

//Input: gifts = [25,64,9,4,100], k = 4
//Output: 29
//Explanation :
//The gifts are taken in the following way:
//In the first second, the last pile is chosen and 10 gifts are left behind.
//Then the second pile is chosen and 8 gifts are left behind.
//After that the first pile is chosen and 5 gifts are left behind.
//Finally, the last pile is chosen again and 3 gifts are left behind.
//The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.


import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.Math.sqrt;

public class PickGifts {
    public static void main(String[] args) {
        PickGifts pickGifts = new PickGifts();
        System.out.println(pickGifts.pickGifts(new int[]{25, 64, 9, 4, 100}, 4));
    }

    public int pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int gift : gifts) {
            heap.offer(gift);
        }
        System.out.println(heap);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            int max = heap.poll();
            int sqrt = (int) sqrt(max);
            heap.offer(sqrt);
        }
        while (!heap.isEmpty()) {
            sum += heap.poll();
        }
        return sum;
    }
}
