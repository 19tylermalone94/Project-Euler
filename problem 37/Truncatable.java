public class Truncatable {

    public static void main(String[] args) {
        int[] truncSum = {0, 0};
        for (int i = 10; truncSum[1] < 11; ++i) {
            truncSum = (isTruncPrime(String.valueOf(i))) ? new int[]{truncSum[0] + i, truncSum[1] + 1} : truncSum;
        }
        System.out.println(truncSum[0]);
    }

    static boolean isTruncPrime(String n) {
        return truncBack(n) && truncFront(n);
    }

    static boolean truncBack(String n) {
        return (n.length() == 0) ? true : (isPrime(Long.parseLong(n), 2)) ? truncBack(n.substring(0, n.length() - 1)) : false;
    }

    static boolean truncFront(String n) {
        return (n.length() == 0) ? true : (isPrime(Long.parseLong(n), 2)) ? truncFront(n.substring(1)) : false;
    }

    static boolean isPrime(long n, int i) {
        return (n <= 1) ? false : (i * i > n) ? true : (n % i == 0) ? false : isPrime(n, i + 1);
    }

}