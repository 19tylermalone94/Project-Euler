import java.math.BigInteger;

public class CountingCombos {

    public static void main(String[] args) {
        int count = 0;
        for (int n = 1; n <= 100; ++n) {
            for (int r = 1; r < n; ++r) {
                count += (C(n, r).compareTo(BigInteger.valueOf(1000000)) > 0) ? 1 : 0;
            }
        }
        System.out.println(count);
    }

    static BigInteger C(int n, int r) {
        return fac(BigInteger.valueOf(n)).divide(fac(BigInteger.valueOf(r)).multiply(fac(BigInteger.valueOf(n - r))));
    }

    static BigInteger fac(BigInteger n) {
        return (n.equals(BigInteger.ONE)) ? n : n.multiply(fac(n.subtract(BigInteger.ONE)));
    }

}