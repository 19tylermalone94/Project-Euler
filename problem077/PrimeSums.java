import java.util.ArrayList;

public class PrimeSums {

    static int MAX_PRIMES = 10000;

    static ArrayList<Integer> primes = new ArrayList<>();

    static void loadPrimes() {
        for (int n = 2; primes.size() < MAX_PRIMES; ++n) {
            if (isPrime(n, 2)) primes.add(n);
        }
    }

    static boolean isPrime(int n, int i) {
        return (i * i > n) ? n > 1 : (n % i == 0) ? false : isPrime(n, i + 1);
    }

    static long makeChange(long amount, int p) {
        if (amount == 0) {
            return 1;
        } else if (p > primes.size() || primes.get(p) > amount) {
            return 0;
        } else {
            return makeChange(amount - primes.get(p), p) + makeChange(amount, p + 1);
        }
    }

    public static void main(String[] args) {
        loadPrimes();
        int n;
        for (n = 10; makeChange(n, 0) < 5000; ++n);
        System.out.println(n);
    }

}