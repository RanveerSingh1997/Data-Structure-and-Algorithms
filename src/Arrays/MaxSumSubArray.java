package Arrays;

public class MaxSumSubArray {

    public static void main(String[] args) {
        System.out.println(maximumSubarraySum(new int[]{100, 200, 300, 400}, 2));
        System.out.println(maximumSubarraySum(new int[]{100, 200, 300, 400}, 1));
        System.out.println(maximumSubarraySum(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 4));
    }

    public static long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        int maximumSum = windowSum;
        for (int j = k; j < n; j++) {
            windowSum += nums[j];
            windowSum -= nums[j - k];
            maximumSum = Math.max(maximumSum, windowSum);
        }
        return maximumSum;
    }
}
