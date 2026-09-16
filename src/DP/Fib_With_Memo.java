package DP;

public class Fib_With_Memo {
    static Long[] memo = new Long[1000];
    static int counter = 0;

    public static Long fib(int n) {
        counter++;
        if (memo[n] != null) {
            return memo[n];
        }
        if (n == 0 || n == 1) {
            return (long) n;
        }
        memo[n] = fib(n - 1) + fib(n - 2);
        return memo[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println(counter);
    }
}
