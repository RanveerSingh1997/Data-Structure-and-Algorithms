package Arrays;

public class LongestOnesII {

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        System.out.println(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));

    }

    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int n = nums.length;
        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            if (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
        }
        return n - left;
    }
}
