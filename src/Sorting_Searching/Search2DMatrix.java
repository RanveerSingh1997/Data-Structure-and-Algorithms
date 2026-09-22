package Sorting_Searching;

public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}};
        int target1 = 10;
        System.out.println(searchMatrix(matrix1, target1));
        int[][] matrix2 = {{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}};
        int target2 = 15;
        System.out.println(searchMatrix(matrix2, target2));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int row = middle / cols;
            int col = middle % cols;
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return false;
    }
}
