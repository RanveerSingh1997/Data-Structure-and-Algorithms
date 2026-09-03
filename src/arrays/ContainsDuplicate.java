package arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Contains Duplicate
 * Link: <a href="https://leetcode.com/problems/contains-duplicate/">...</a>
 * <p>
 * Approach:
 * Use a HashSet to track seen elements. If an element already exists in the set, return true.
 * <p>
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}
