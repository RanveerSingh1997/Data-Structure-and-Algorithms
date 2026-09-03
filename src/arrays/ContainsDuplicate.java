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

    public static void main(String[] args) {
        ContainsDuplicate solver = new ContainsDuplicate();

        int[] test1 = {1, 2, 3, 1};
        int[] test2 = {1, 2, 3, 4};
        int[] test3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};

        System.out.println("=== Testing ContainsDuplicate ===");
        System.out.println("[1, 2, 3, 1] -> Expected: true  | Output: " + solver.containsDuplicate(test1));
        System.out.println("[1, 2, 3, 4] -> Expected: false | Output: " + solver.containsDuplicate(test2));
        System.out.println("[1, 1, 1...] -> Expected: true  | Output: " + solver.containsDuplicate(test3));
    }
}
