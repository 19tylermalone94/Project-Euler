import java.util.ArrayList;
import java.util.List;

public class QuadPrimes {

    // n*n + a * n + b : |a| < 1000 and |b| <= 1000
    public static void main(String[] args) {
        ArrayList<Integer> bestCoeff = new ArrayList<>(List.of(0, 0, 0)); // maxPrimes, a, b
        for (int a = -999; a < 1000; ++a) {
            for (int b = -1000; b <= 1000; ++b) {
                int n = 0;
                while (isPrime(n*n + a*n + b)) {
                    bestCoeff = (++n > bestCoeff.get(0)) ? new ArrayList<>(List.of(n, a, b)) : bestCoeff;
                }
            }
        }
        System.out.println(bestCoeff.get(1) * bestCoeff.get(2));
    }

    public static boolean isPrime(long n) {
        n *= (n < 0) ? -1 : 1;
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) return false;
        }
        return true;
    }

}