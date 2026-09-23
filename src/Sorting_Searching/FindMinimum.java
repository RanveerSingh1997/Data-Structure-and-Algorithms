package Sorting_Searching;

public class FindMinimum {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3, 4, 5, 6, 1, 2}));
        System.out.println(findMin(new int[]{4, 5, 0, 1, 2, 3}));
        System.out.println(findMin(new int[]{4, 5, 6, 7}));
    }

    private static int findMin(int[] nums) {
        int minNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minNum) {
                minNum = nums[i];
            }
        }
        return minNum;
    }
}
