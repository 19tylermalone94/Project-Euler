import java.math.BigInteger;
import java.util.ArrayList;

public class Convergence {

    static int MAX_TERMS = 100;

    static ArrayList<Integer> seq = new ArrayList<>() {
        {
            int m = 2;
            for (int i = 0; i < MAX_TERMS; ++i) {
                if ((i + 2) % 3 == 0) {
                    add(m);
                    m += 2;
                } else {
                    add(1);
                }
            }
        }
    };

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

    static Frac converge(int i) {
        return (i == MAX_TERMS) ? new Frac(BigInteger.ZERO, BigInteger.ONE) 
        : new Frac(BigInteger.valueOf(seq.get(i)), BigInteger.ONE).add(new Frac(BigInteger.ONE, converge(i + 1)));
    }

    public static void main(String[] args) {
        System.out.println(seq.toString());
        Frac frac = new Frac(BigInteger.TWO, BigInteger.ONE).add(new Frac(BigInteger.ONE, converge(0)));
        System.out.println(frac.toString());
        int sum = 0;
        for (int i = 0; i < frac.n.toString().length(); ++i) {
            sum += frac.n.toString().charAt(i) - '0';
        }
        System.out.println(sum);
    }

}