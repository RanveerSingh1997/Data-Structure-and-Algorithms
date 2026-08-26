package arrays;

import java.util.*;

public class TopKFrequentElements {
    public int[] topFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>(
                Comparator.comparingInt(count::get)
        );

        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.poll();
        }

        return ans;
    }
}
