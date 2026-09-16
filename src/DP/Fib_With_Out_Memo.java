package DP;

public class Fib_With_Out_Memo {
    static int counter = 0;

    public static Long fib(int n) {
        counter++;

        if (n == 0 || n == 1) {
            return (long) n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println(counter);
    }
}
