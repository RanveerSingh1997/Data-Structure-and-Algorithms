package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Two Sum
 * Link: <a href="https://leetcode.com/problems/two-sum/">...</a>
 * <p>
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}