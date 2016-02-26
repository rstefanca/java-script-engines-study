package cz.codingmonkey.scripting;

/**
 * @author Richard Stefanca
 */
public class Fibonacci {

    public static long fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

}
