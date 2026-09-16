package DP;

public class Fib_With_Bottom_UP {
    static int counter = 0;

    public static int fib(int n) {
        int[] fibList = new int[n + 1];
        fibList[0] = 0;
        fibList[1] = 1;
        for (int i = 2; i <= n; i++) {
            counter++;
            fibList[i] = fibList[i - 1] + fibList[i - 2];
        }
        return fibList[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(7));
        System.out.println(counter);
    }
}
