package Sorting_Searching;

public class MinEatingSpeed {
    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{1, 4, 3, 2}, 9));
        System.out.println(minEatingSpeed(new int[]{25, 10, 23, 4}, 4));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        System.out.println("MAX RIGH" + right);
        while (left < right) {
            int k = left + (right - left) / 2;
            System.out.println("VLAUE OF K" + k);
            int hours = 0;
            for (int pile : piles) {
                hours += (pile + k - 1) / k;
                System.out.println("HOURS CALUCLATION" + hours);
            }
            if (hours <= h) {
                right = k;
            } else {
                left = k + 1;
            }
        }
        return left;
    }
}
