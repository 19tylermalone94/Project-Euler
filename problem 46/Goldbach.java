public class Goldbach {

    public static void main(String[] args) {
        int i = 3;
        while (!contradictsGoldbach(i)) {
            i += 2;
        }
        System.out.println(i);
    }

    static boolean contradictsGoldbach(long n) {
        if (isPrime(n, 2)) return false;
        for (long i = 2; i < n; ++i) {
            if (isPrime(i, 2) && isTwiceSquare(n - i) || isPrime(n - i, 2) && isTwiceSquare(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean isTwiceSquare(long n) {
        return Math.sqrt(n / 2) == (int)Math.sqrt(n / 2);
    }

    static boolean isPrime(long n, long i) {
        return (i * i > n) ? true : (n % i == 0 || n == 1) ? false : isPrime(n, i + 1);
    }

}