package Sorting_Searching;

public class SearchInsert {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{-1, 0, 2, 4, 6, 8}, 5));
        System.out.println(searchInsert(new int[]{-1, 0, 2, 4, 6, 8}, 2));
        System.out.println(searchInsert(new int[]{-1, 0, 2, 4, 6, 8}, 8));
        System.out.println(searchInsert(new int[]{-1, 0, 2, 4, 6, 8}, 10));
    }

    private static int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right) {
            return left;
        }

        int middle = left + (right - left) / 2;
        if (nums[middle] == target) {
            return middle;
        }
        if (target < nums[middle]) {
            return binarySearch(nums, left, middle - 1, target);
        }
        return binarySearch(nums, middle + 1, right, target);
    }


    public static int searchInsert(int[] nums, int target) {
        int index = binarySearch(nums, 0, nums.length - 1, target);
        return index;
    }
}
