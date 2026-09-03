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

    public static void main(String[] args) {
        TopKFrequentElements solver = new TopKFrequentElements();

        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println("=== Testing TopKFrequentElements ===");
        System.out.println("nums=[1,1,1,2,2,3], k=2 -> Output: " + Arrays.toString(solver.topFrequent(nums1, k1)) + " (Expected [1, 2] in any order)");

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("nums=[1], k=1           -> Output: " + Arrays.toString(solver.topFrequent(nums2, k2)) + " (Expected [1])");
    }
}
