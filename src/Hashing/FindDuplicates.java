package Hashing;

import java.util.HashMap;

public class FindDuplicates {
    public static void main(String[] args) {
        FindDuplicates findDuplicates = new FindDuplicates();
        System.out.println("Most frequent element: " + findDuplicates.findDuplicate(new int[]{2, 3, 5, 5, 6, 2, 2, 5, 5, 5, 3, 3, 3, 3, 3}));
    }

    public int findDuplicate(int[] array) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : array) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        int maxCount = 0;
        int mostFrequent = -1;
        for (java.util.Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }
}
