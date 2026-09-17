package Arrays;

public class ContainerArea {
    public static int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;
        while (left < right) {
            if (heights[left] > heights[right]) {
                maxArea = Math.max(maxArea, (right - left) * heights[right]);
                right--;
            } else if (heights[left] < heights[right]) {
                maxArea = Math.max(maxArea, (right - left) * heights[left]);
                left++;
            } else {
                maxArea = Math.max(maxArea, (right - left) * heights[left]);
                right--;
                left++;
            }

        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 7, 2, 5, 4, 7, 3, 6}));
    }
}
