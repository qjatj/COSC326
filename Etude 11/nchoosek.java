import java.util.Scanner;

/**
 * A program that takes user input n and k for a binomial coefficient using 64-bit integers.
 * Author: Patrick Baxter, Sean Kim
 */

public class nchoosek {
    //numerator values
    private static long[] num;
    private static long n = 0, k = 0, value;

    /**
     * User input for n and k values using longs
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a 'n' and 'k' value in 'n k' format e.g. 52 5.");
        String line;
        //While user input is not empty, keep calling pascals method using input values.
        while (sc.hasNext()) {
            value = sc.nextLong();
            if(n == 0){
                n = value;
            }
            else{
                k = value;
            }
            if(n != 0 && k != 0){
                nk(n, k);
                n = 0;
                k = 0;
            }
        }
    }

    /**
     * Creates numerator from values n to n - k + 1, then divides the denominator
     * values at primes 2,3, and 5 and cancels them out from the numerator.
     *
     * @param n natural number >= k
     * @param k natural number >= 0
     */
    private static void nk(long n, long k) {
        num = new long[(int) k];
        long total = 0;
        for (int i = 0; i < k; i++) {
            num[i] = n - i;
        }
        for (int i = 2; i <= k; i++) {
            int j = i;
            while (j != 0 & j % 2 == 0) {
                j /= 2;
                cancelOut(2);
            }
            while (j != 0 & j % 3 == 0) {
                j /= 3;
                cancelOut(3);
            }
            while (j != 0 & j % 5 == 0) {
                j /= 5;
                cancelOut(5);
            }
            if (j > 1) {
                cancelOut(j);
            }
        }
        total += num[0];
        for (int i = 1; i < k; i++) {
            total *= num[i];
        }
        System.out.println(total);
    }

    /**
     * Checks through the numerators for values divisible by the input and cancels them out
     * @param c current value to cancel out from num
     */
    private static void cancelOut(long c) {
        long x = c;
        for (int i = 0; i < num.length; i++) {
            if (num[i] % x == 0) {
                num[i] /= x;
                break;
            }
        }
    }
}