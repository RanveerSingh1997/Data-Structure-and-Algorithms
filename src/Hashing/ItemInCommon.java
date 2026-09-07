package Hashing;

import java.util.HashMap;

public class ItemInCommon {

    public static boolean itemInCommon(int[] array1, int[] array2) {
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        for (int i : array1) {
            hashMap.put(i, true);
        }
        for (int j : array2) {
            if (hashMap.get(j) != null) return true;
        }
        return false;
    }

    static void main() {
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        int[] c = {1, 8, 6};
        int[] d = {2, 4, 7};
        System.out.println(itemInCommon(a, b));
        System.out.println(itemInCommon(a, c));
        System.out.println(itemInCommon(a, d));
        System.out.println(itemInCommon(b, c));
        System.out.println(itemInCommon(a, c));
    }
}
