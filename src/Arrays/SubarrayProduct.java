package Arrays;

public class SubarrayProduct {

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 1, 1}, 1));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int left = 0;
        int count = 0;
        int prod = 1;
        int n = nums.length;
        for (int right = 0; right < n; right++) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            count += right - left + 1;
        }

        return count;
    }
}
