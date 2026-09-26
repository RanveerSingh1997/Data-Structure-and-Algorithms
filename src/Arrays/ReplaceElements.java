package Arrays;

import java.util.Arrays;

public class ReplaceElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(replaceElements(new int[]{2, 4, 5, 3, 1, 2})));
        System.out.println(Arrays.toString(replaceElements(new int[]{3, 3})));
    }

    public static int[] replaceElements(int[] arr) {
        int maxRight = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int current = arr[i];
            arr[i] = maxRight;
            maxRight = Math.max(maxRight, current);
        }
        return arr;
    }
}
