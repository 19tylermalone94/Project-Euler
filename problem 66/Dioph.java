import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Dioph {

    static class Frac {
        BigInteger n;
        BigInteger d;

        Frac(BigInteger numerator, BigInteger denominator) {
            this.n = numerator;
            this.d = denominator;
        }

        Frac(BigInteger n, Frac d) {
            this.n = n.multiply(d.d);
            this.d = d.n;
        }

        Frac add(Frac other) {
            Frac sum = new Frac(n.multiply(other.d).add(d.multiply(other.n)), d.multiply(other.d));
            return simplify(sum);
        }

        Frac simplify(Frac frac) {
            BigInteger gcd = gcd(frac.n, frac.d);
            return new Frac(frac.n.divide(gcd), frac.d.divide(gcd));
        }
    
        private BigInteger gcd(BigInteger a, BigInteger b) {
            return (b.equals(BigInteger.ZERO)) ? a : gcd(b, a.mod(b));
        }

        @Override
        public String toString() {
            return n + "/" + d;
        }
    }

    static Frac converge(int i, List<Integer> seq, int max) {
        return (i == max) ? new Frac(BigInteger.ZERO, BigInteger.ONE) 
        : new Frac(BigInteger.valueOf(seq.get(i % seq.size())), BigInteger.ONE).add(new Frac(BigInteger.ONE, converge(i + 1, seq, max)));
    }

    public static List<Integer> periodList(int n) {
        int a0 = (int)Math.sqrt(n);
        List<Integer> periodList = new ArrayList<>();
        if (a0 * a0 == n) {
            return periodList; // Return empty list for perfect squares
        }
        int a = a0, m = 0, d = 1;

        do {
            m = d * a - m;
            d = (n - m * m) / d;
            a = (a0 + m) / d;
            periodList.add(a);
        } while (a != 2 * a0);

        return periodList;
    }

    public static void main(String[] args) {
        BigInteger maxX = BigInteger.ZERO;
        int bigD = 0;
        for (int d = 1; d <= 1000; ++d) {
            int dd = (int)Math.sqrt(d);
            if (dd * dd == d) continue;
            int i = 1;
            while (true) {
                Frac frac = new Frac(BigInteger.valueOf((int)Math.sqrt(d)), BigInteger.ONE).add(new Frac(BigInteger.ONE, converge(0, periodList(d), i)));
                BigInteger x = frac.n;
                BigInteger y = frac.d;
                if (x.pow(2).subtract(y.pow(2).multiply(BigInteger.valueOf(d))).equals(BigInteger.ONE)) {
                    if (x.compareTo(maxX) > 0) {
                    maxX = x;
                    bigD = d;
                    }
                    break;
                }
                ++i;
            }
        }
        System.out.println("Biggest x for d <= 1000: " + maxX.toString());
        System.out.println("d value associated with largest x for d <= 1000: " + bigD);
    }

}