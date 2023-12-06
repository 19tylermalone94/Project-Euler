import java.util.BitSet;

public class CountingFracs {

    static BitSet primes = new BitSet();

    static void genPrimes() {
        for (int i = 2; i <= 1000000; ++i) {
            if (isPrime(i, 2)) {
                primes.set(i);
            } else {
                primes.clear(i);
            }
        }
    }

    static boolean isPrime(int n, int i) {
        return (i * i > n) ? n > 1 : (n % i == 0) ? false : isPrime(n, i + 1);
    }

    // n * PI p|n (1 - 1/p)
    static double totient(int n) {
        double product = 1;
        for (int i = 2; i * i <= n; i += ( i == 2) ? 1 : 2) {
            if (n % i == 0) {
                if (primes.get(i)) {
                    product *= 1.0 - (1.0 / i);
                }
                if (n / i != i && primes.get(n / i)) {
                    product *= 1.0 - (1.0 / (n / i));
                }
            }
        }
        return (product == 1) ? n - 1 : n * product;
    }

    public static void main(String[] args) {
        genPrimes();
        long sum = 0;
        for (int i = 2; i <= 1000000; ++i) {
            sum += totient(i);
        }
        System.out.println(sum);
    }

}