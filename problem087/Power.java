import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Power {

    static List<Integer> primes = IntStream.range(2, 8000).filter(i -> isPrime(i, 2)).boxed().toList();

    static boolean isPrime(int n, int i) {
        return i * i > n ? n > 1 : n % i == 0 ? false : isPrime(n, i + 1);
    }

    static int pow(int n, int p) {
        return (int)Math.pow(primes.get(n), p);
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        int lim = 50000000;
        int count = 0;
        for (int t = 0; t < primes.size() && pow(t, 4) < lim; ++t) {
            for (int c = 0; c <primes.size() && pow(t, 4) + pow(c, 3) < lim; ++c) {
                for (int s = 0; s < primes.size() && pow(t, 4) + pow(c, 3) + pow(s, 2) < lim; ++s) {
                    int num = pow(t, 4) + pow(c, 3) + pow(s, 2);
                    count += set.add(num) ? 1 : 0;
                }
            }
        }
        System.out.println(count);
    }


}