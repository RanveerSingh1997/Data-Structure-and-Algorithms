package Arrays;

import java.util.Arrays;

public class SortColors {
    // Why Dutch National Flag works
    // low will handle all values form 0 to low-1;
    // mid will handle all values from low to mid-1;
    // high will handle all values from hig+1 to high;
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 0, 1, 2};
        sortColors(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = new int[]{2, 1, 0};
        sortColors(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    public static void sortColors(int[] nums) {
        int n = nums.length;
        int low = 0;
        int mid = 0;
        int high = n - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                nums[mid] = nums[low];
                nums[low] = 0;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                nums[mid] = nums[high];
                nums[high] = 2;
                high--;
            }
        }
    }
}
