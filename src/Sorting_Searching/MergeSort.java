package Sorting_Searching;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array1 = {1, 4, 6, 7, 8, 11};
        int[] array2 = {2, 3, 5, 9, 10};
        System.out.println("----------Merge Function to Merge two sorted array-----------------");
        System.out.println(Arrays.toString(merge(array1, array2)));
        System.out.println("----------Merge Sorting----------");
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 11};
        int[] sortedElements = mergeSort(nums);
        System.out.println(Arrays.toString(sortedElements));
    }

    private static int[] mergeSort(int[] nums) {
        if (nums.length == 1) return nums;
        int midIndex = nums.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(nums, 0, midIndex));
        int[] right = mergeSort(Arrays.copyOfRange(nums, midIndex, nums.length));
        return merge(left, right);
    }

    public static int[] merge(int[] array1, int[] array2) {
        int[] combined = new int[array1.length + array2.length];
        int index = 0;
        int i = 0;
        int j = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                combined[index] = array1[i];
                index++;
                i++;
            } else {
                combined[index] = array2[j];
                index++;
                j++;
            }
        }
        while (i < array1.length) {
            combined[index] = array1[i];
            index++;
            i++;
        }
        while (j < array2.length) {
            combined[index] = array2[j];
            index++;
            j++;
        }
        return combined;
    }
}
