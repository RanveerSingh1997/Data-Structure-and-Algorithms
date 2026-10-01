package Arrays;

import java.util.Arrays;

public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums1 = new int[]{0, 0, 1, 2, 0, 5};
        moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = new int[]{0, 1, 0};
        moveZeroes(nums2);
        System.out.println(Arrays.toString(nums2));

    }

    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
        }
    }
}
