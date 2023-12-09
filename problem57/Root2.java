import java.math.BigInteger;

public class Root2 {

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

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 1000; ++i) {
            Frac expansion = new Frac(BigInteger.ONE, BigInteger.ONE).add(expand(i));
            sum += (expansion.n.toString().length() > expansion.d.toString().length()) ? 1 : 0;
        }
        System.out.println(sum);
    }

    static Frac expand(int i) {
        return (i == 0) ? new Frac(BigInteger.ZERO, BigInteger.ONE) 
        : new Frac(BigInteger.ONE, new Frac(BigInteger.TWO, BigInteger.ONE).add(expand(i - 1)));
    }

}