package Sorting_Searching;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;
            while (j > -1 && temp < nums[j]) {
                nums[j + 1] = nums[j];
                nums[j] = temp;
                j--;
            }
        }
    }
}
