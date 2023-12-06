import java.util.BitSet;

public class Totient {

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

    // n * PI p|n (1 - 1/p)
    static double productForm(int n) {
        double product = 1;
        for (int i = 1; i * i <= n; i += ( i == 2) ? 1 : 2) {
            if (n % i == 0) {
                if (primes.get(i)) {
                    product *= 1.0 - (1.0 / i);
                }
                if (primes.get(n / i)) {
                    product *= 1.0 - (1.0 / (n / i));
                }
            }
        }
        return n * product;
    }

    static boolean isPrime(int n, int i) {
        return (i * i > n) ? n > 1 : (n % i == 0) ? false : isPrime(n, i + 1);
    }

    public static void main(String[] args) {
        genPrimes();
        // int max = 0;
        // double maxQuotient = 0;
        // for (int n = 2; n <= 1000000; n += 2) {
        //     double quotient = n / productForm(n) * 1.0;
        //     if (quotient > maxQuotient) {
        //         maxQuotient = quotient;
        //         max = n;
        //     }
        // }
        // System.out.println(max);
        System.out.println(productForm(87109));
        System.out.println(productForm(9));
    }

}