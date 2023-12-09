import java.util.BitSet;
import java.util.ArrayList;
import java.util.Collections;

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

    static boolean isPrime(int n, int i) {
        return (i * i > n) ? n > 1 : (n % i == 0) ? false : isPrime(n, i + 1);
    }

    static boolean isPerm(long a, long b) {
        ArrayList<Long> set1 = new ArrayList<>();
        ArrayList<Long> set2 = new ArrayList<>();
        while (a > 0) {
            set1.add(a % 10);
            a /= 10;
        }
        while (b > 0) {
            set2.add(b % 10);
            b /= 10;
        }
        Collections.sort(set1);
        Collections.sort(set2);
        return set1.equals(set2);
    }

    public static void main(String[] args) {
        genPrimes();
        int min = 0;
        double minQuotient = Double.MAX_VALUE;
        for (int n = 3; n <= 10000000; n += 2) {
            double totient = productForm(n);
            if (isPerm(Math.round(totient), n)) {
                double quotient = n / totient * 1.0;
                if (quotient < minQuotient) {
                    minQuotient = quotient;
                    min = n;
                }
            }
        }
        System.out.println(min);        
    }

}