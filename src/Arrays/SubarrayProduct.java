package Arrays;

public class SubarrayProduct {

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 1, 1}, 1));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int count = 0;
        int n = nums.length;
        return count;
    }
}
