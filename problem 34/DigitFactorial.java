public class DigitFactorial {

    /*
     * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
     * 9! * 7 = 2'540'160, and 9! * 8 = 2'903'040 
     * We can see that these numbers cannot have eight or more digits. 
     */
    public static void main(String[] args) {
        int specialSum = 0;
        for (int i = 3; i < 10000000; ++i) {
            specialSum += (isSpecial(i, i, 0)) ? i : 0;
        }
        System.out.println(specialSum);
    }

    static boolean isSpecial(int n, int target, int sum) {
        return (n == 0) ? sum == target : isSpecial(n / 10, target, sum + factorial(n % 10));
    }

    static int factorial(int n) {
        return (n < 2) ? 1 : n * factorial(n - 1);
    }

}