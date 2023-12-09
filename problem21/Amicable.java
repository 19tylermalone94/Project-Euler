public class Amicable {

    public static int divisorSum(int n) {
        int divisorSum = 1;
        for (int i = 2; i * i <= n; ++i) {
            divisorSum += (n % i == 0) ? i + (n / i) : 0;
        }
        return divisorSum;
    }

    public static boolean isAmicable(int n) {
        return n == divisorSum(divisorSum(n)) && n != divisorSum(n);
    }

    public static void main(String[] args) {
        int amicableSum = 0;

        for (int i = 2; i < 10000; ++i) {
            amicableSum += (isAmicable(i)) ? i : 0;
        }
        System.out.println(amicableSum);
    }

}