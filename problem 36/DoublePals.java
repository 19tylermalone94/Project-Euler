import java.math.BigInteger;

public class DoublePals {

    public static void main(String[] arg) {
        int palSum = 0;
        for (int i = 1; i < 1000000; ++i) {
            palSum += (isPal(String.valueOf(i)) && isPal(String.valueOf(toBinary(i, BigInteger.ZERO, BigInteger.ONE)))) ? i : 0;
        }
        System.out.println(palSum);
    }

    static BigInteger toBinary(int n, BigInteger binary, BigInteger decPlace) {
        return (n == 0) ? binary : toBinary(n / 2, binary.add(decPlace.multiply(BigInteger.valueOf(n % 2))), decPlace.multiply(BigInteger.TEN));
    }

    static boolean isPal(String n) {
        return (n.length() <= 1) ? true
        : (n.charAt(0) != n.charAt(n.length() - 1)) ? false
        : isPal(n.substring(1, n.length() - 1));
    }

}