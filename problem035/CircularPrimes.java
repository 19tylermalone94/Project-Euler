public class CircularPrimes {

    public static void main(String[] args) {
        int numCircular = 0;
        for (int i = 2; i < 1000000; ++i) {
            numCircular += (isCircularPrime(String.valueOf(i))) ? 1 : 0;
        }
        System.out.println(numCircular);
    }

    static boolean isCircularPrime(String n) {
        for (int i = 0; i < n.length(); ++i) {
            if (!isPrime(Integer.parseInt(n))) return false;
            n = n.substring(1) + n.charAt(0);
        }
        return true;
    }

    static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) return false;
        }
        return true;
    }

}