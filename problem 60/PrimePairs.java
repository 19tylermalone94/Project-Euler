import java.util.ArrayList;
import java.util.BitSet;

public class PrimePairs {

    static int MAX_NUM_PRIMES = 10000;
    static int LIMIT = 10000;

    static BitSet primality = new BitSet() {
        {
            for (int i = 2; i < Integer.MAX_VALUE; i += (i == 2) ? 1 : 2) {
                if (isPrime(i, 2)) {
                    set(i);
                } else {
                    clear(i);
                }
            }
            System.out.println("Hello");
        }
    };

    static ArrayList<Integer> primes = new ArrayList<>() {
        {
            for (int i = 0; size() < MAX_NUM_PRIMES; ++i) {
                if (isPrime(i, 2) && i != 2 && i != 5) add(i);
            }
            System.out.println("Hello");
        }
    };

    static boolean isPrimePairSet(ArrayList<Integer> set) {
        for (int i = 0; i < set.size(); ++i) {
            for (int j = 0; j < set.size(); ++j) {
                if (i == j) continue;
                int bigNum = set.get(i) * (int)(Math.pow(10, len(set.get(j), 0))) + set.get(j);
                if (!primality.get(bigNum)) return false;
            }
        }
        return true;
    }

    static int len(long n, int len) {
        return (n == 0) ? len : len(n / 10, len + 1);
    }

    static boolean isPrime(long n, long i) {
        return (i * i > n) ? n > 1 : (n % i == 0) ? false : isPrime(n, i + 1);
    }

    public static void main(String[] args) {
        for (int a = 0; a < LIMIT - 4; ++a) {
            ArrayList<Integer> set = new ArrayList<>();
            set.add(primes.get(a));
            for (int b = a + 1; b < LIMIT - 3; ++b) {
                set.add(primes.get(b));
                if (!isPrimePairSet(set)) {
                    set.remove(set.size() - 1);
                    continue;
                }
                for (int c = b + 1; c < LIMIT - 2; ++c) {
                    set.add(primes.get(c));
                    if (!isPrimePairSet(set)) {
                        set.remove(set.size() - 1);
                        continue;
                    }
                    for (int d = c + 1; d < LIMIT - 1; ++d) {
                        set.add(primes.get(d));
                        if (!isPrimePairSet(set)) {
                            set.remove(set.size() - 1);
                            continue;
                        }
                        for (int e = d + 1; e < LIMIT; ++e) {
                            set.add(primes.get(e));
                            if (isPrimePairSet(set)) {
                                System.out.println(primes.get(a) + primes.get(b) + primes.get(c) + primes.get(d) + primes.get(e));
                            }
                            set.remove(set.size() - 1);
                        }
                        set.remove(set.size() - 1);
                    }
                    set.remove(set.size() - 1);
                }
                set.remove(set.size() - 1);
            }
            set.remove(set.size() - 1);
        }
    }

}