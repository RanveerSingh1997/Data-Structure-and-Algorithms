package Sorting_Searching;

public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(search(new int[]{-1, 0, 2, 4, 6, 8}, 4));
        System.out.println(search(new int[]{-1, 0, 2, 4, 6, 8}, 3));
    }

    public static int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private static int binarySearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = left + (right - left) / 2;
        if (nums[middle] == target) {
            return middle;
        }
        if (nums[middle] < target) {
            return binarySearch(nums, target, middle + 1, right);
        }
        return binarySearch(nums, target, left, middle - 1);
    }


}