package Sorting_Searching;

public class ArrangingCoins {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(10));
        System.out.println(arrangeCoins(4));
        System.out.println(arrangeCoins(12));
    }

    public static int arrangeCoins(int n) {
        int left = 0;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long coinsNeeded = (long) mid * (mid + 1) / 2;
            if (coinsNeeded <= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
        // return maxNumber(1, n, n);
    }

    int maxNumber(int left, int right, int target) {
        if (left > right) {
            return 0;
        }
        int middle = left + (right - left) / 2;
        int max = middle * (middle + 1) / 2;
        if (max == middle) {
            return middle;
        }
        if (max > middle) {
            return maxNumber(0, middle - 1, target);
        }
        return maxNumber(middle + 1, right, target);
    }
}
