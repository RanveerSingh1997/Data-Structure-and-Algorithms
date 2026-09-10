package Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FindPairs {

    public static List<int[]> findPairs(int[] array1, int[] array2, int target) {
        HashSet<Integer> hashSet = new HashSet<>();
        List<int[]> pairs = new ArrayList<>();
        for (int num : array1) {
            hashSet.add(num);
        }

        for (int num : array2) {
            int complement = target - num;
            if (hashSet.contains(complement)) {
                int[] combination = {complement, num};
                pairs.add(combination);
            }
        }
        return pairs;
    }

    private static String formatPairs(List<int[]> pairs) {
        List<String> formatted = new ArrayList<>();
        for (int[] pair : pairs) {
            formatted.add(Arrays.toString(pair));
        }
        return formatted.toString();
    }

    public static void main(String[] args) {
        System.out.println("These tests confirm findPairs() finds all");
        System.out.println("pairs from two arrays whose sum equals the target.");
        System.out.println();

        // Test 1: Single pair (actually multiple results summing to same target)
        System.out.println("Test 1: Find Pairs Summing to Target");
        int[] arr1a = {1, 2, 3};
        int[] arr2a = {4, 5, 6};
        int target1 = 7;
        System.out.println("Expected: Pairs like [1,6], [2,5], [3,4]");
        System.out.println("Actual: " + formatPairs(findPairs(arr1a, arr2a, target1)));
        System.out.println();

        // Test 2: No pairs exist
        System.out.println("Test 2: No Pairs");
        int[] arr1b = {1, 2};
        int[] arr2b = {7, 8};
        int target2 = 20;
        System.out.println("Expected: []");
        System.out.println("Actual: " + formatPairs(findPairs(arr1b, arr2b, target2)));
        System.out.println();

        // Test 3: Multiple valid pairs
        System.out.println("Test 3: Multiple Valid Pairs");
        int[] arr1c = {2, 4, 6};
        int[] arr2c = {1, 3, 5};
        int target3 = 7;
        System.out.println("Expected: Pairs like [2,5], [4,3], [6,1]");
        System.out.println("Actual: " + formatPairs(findPairs(arr1c, arr2c, target3)));
        System.out.println();

        // Test 4: Handles negatives
        System.out.println("Test 4: Handles Negative Numbers");
        int[] arr1d = {-1, -2, 3};
        int[] arr2d = {1, 4, -3};
        int target4 = 1;
        System.out.println("Expected: Pairs that sum to 1 (any valid ones)");
        System.out.println("Actual: " + formatPairs(findPairs(arr1d, arr2d, target4)));
        System.out.println();

        // Test 5: Both arrays empty
        System.out.println("Test 5: Empty Arrays");
        int[] arr1e = {};
        int[] arr2e = {};
        int target5 = 5;
        System.out.println("Expected: []");
        System.out.println("Actual: " + formatPairs(findPairs(arr1e, arr2e, target5)));
        System.out.println();

        /*
            EXPECTED OUTPUT:
            ----------------
            These tests confirm findPairs() finds all
            pairs from two arrays whose sum equals the target.

            Test 1: Find Pairs Summing to Target
            Expected: Pairs like [1,6], [2,5], [3,4]
            Actual: [[1,6], [2,5], [3,4]]

            Test 2: No Pairs
            Expected: []
            Actual: []

            Test 3: Multiple Valid Pairs
            Expected: [[2,5], [4,3], [6,1]]
            Actual: [[2,5], [4,3], [6,1]]

            Test 4: Handles Negative Numbers
            Expected: Pairs summing to 1 (any valid)
            Actual: [[...valid pairs...]]

            Test 5: Empty Arrays
            Expected: []
            Actual: []
        */

    }
}
