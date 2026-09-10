package Hashing;

import java.util.*;

public class RemoveDuplicates {
    public static List<Integer> removeDuplicates(List<Integer> array) {
        HashSet<Integer> hashSet = new HashSet<>(array);
        return hashSet.stream().toList();
    }

    private static String formatResult(List<Integer> list) {
        List<Integer> sorted = new ArrayList<>(list);
        Collections.sort(sorted);  // for consistent print order
        return sorted.toString();
    }

    public static void main(String[] args) {
        System.out.println("These tests confirm removeDuplicates() removes");
        System.out.println("duplicates and returns only unique values");
        System.out.println("(order is not guaranteed).");
        System.out.println();

        // Test 1: Duplicates removed
        System.out.println("Test 1: Duplicates Removed");
        List<Integer> list1 = Arrays.asList(1, 2, 2, 3, 3, 4);
        System.out.println("Expected: [1, 2, 3, 4]");
        System.out.println("Actual: " + formatResult(removeDuplicates(list1)));
        System.out.println();

        // Test 2: No duplicates
        System.out.println("Test 2: No Duplicates");
        List<Integer> list2 = Arrays.asList(5, 6, 7);
        System.out.println("Expected: [5, 6, 7]");
        System.out.println("Actual: " + formatResult(removeDuplicates(list2)));
        System.out.println();

        // Test 3: All duplicates
        System.out.println("Test 3: All Duplicates");
        List<Integer> list3 = Arrays.asList(8, 8, 8, 8);
        System.out.println("Expected: [8]");
        System.out.println("Actual: " + formatResult(removeDuplicates(list3)));
        System.out.println();

        // Test 4: Empty list
        System.out.println("Test 4: Empty List");
        List<Integer> list4 = new ArrayList<>();
        System.out.println("Expected: []");
        System.out.println("Actual: " + formatResult(removeDuplicates(list4)));
        System.out.println();

        // Test 5: Single element
        System.out.println("Test 5: Single Element");
        List<Integer> list5 = List.of(10);
        System.out.println("Expected: [10]");
        System.out.println("Actual: " + formatResult(removeDuplicates(list5)));
        System.out.println();

        /*
            EXPECTED OUTPUT:
            ----------------
            These tests confirm removeDuplicates() removes
            duplicates and returns only unique values
            (order is not guaranteed).

            Test 1: Duplicates Removed
            Expected: [1, 2, 3, 4]
            Actual: [1, 2, 3, 4]

            Test 2: No Duplicates
            Expected: [5, 6, 7]
            Actual: [5, 6, 7]

            Test 3: All Duplicates
            Expected: [8]
            Actual: [8]

            Test 4: Empty List
            Expected: []
            Actual: []

            Test 5: Single Element
            Expected: [10]
            Actual: [10]
        */

    }
}
