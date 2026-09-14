package Recursion;

public class Factorial {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        System.out.println(factorial.factorial(8));
        System.out.println(factorial.factorial(4));
        System.out.println(factorial.factorial(5));
    }

    public Double factorial(int n) {
        if (n == 1) return 1.0;
        return n * factorial(n - 1);
    }
}
