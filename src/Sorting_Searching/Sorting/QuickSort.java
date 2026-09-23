package Sorting_Searching.Sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        System.out.println("---Pivot Code Testing-----------");
        int[] array = {4, 6, 1, 7, 3, 2, 5};
        int returnedIndex = pivot(array, 0, array.length - 1);
        System.out.println("RETURNED INDEX -> " + returnedIndex);
        System.out.println(Arrays.toString(array));
        System.out.println("---QUICK SORTING Code Testing------");
        quickSort(nums);
        quickSort(array);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] nums) {
        quickSortHelper(nums, 0, nums.length - 1);
    }

    private static void quickSortHelper(int[] nums, int left, int right) {
        if (left < right) {
            int pivotIndex = pivot(nums, left, right);
            quickSortHelper(nums, left, pivotIndex - 1);
            quickSortHelper(nums, pivotIndex + 1, right);
        }
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    private static int pivot(int[] array, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;
        for (int i = pivotIndex + 1; i <= endIndex; i++) {
            if (array[i] < array[pivotIndex]) {
                swapIndex++;
                swap(array, swapIndex, i);
            }
        }
        swap(array, pivotIndex, swapIndex);
        return swapIndex;
    }

}
