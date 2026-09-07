package Hashing;

import java.util.HashMap;

public class FindDuplicates {
    static void main() {
        FindDuplicates findDuplicates = new FindDuplicates();
        System.out.println(findDuplicates.findDuplicate(new int[]{2, 3, 5, 5, 6, 2, 2, 5, 5, 5, 3, 3, 3, 3, 3}));

    }

    public int findDuplicate(int[] array) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : array) {
            int value = hashMap.getOrDefault(num, 0) + 1;
            hashMap.put(num, value);
        }
        int maxValue = -1;
        for (int value : hashMap.keySet()) {
            if (maxValue < hashMap.getOrDefault(value, 0)) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}
