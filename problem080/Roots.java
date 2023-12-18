import java.math.BigInteger;
import java.util.Arrays;

public class Roots {

    static BigInteger newtonMethod(int n, int precision) {
        BigInteger scale = BigInteger.TEN.pow(2 * precision);
        BigInteger bigN = BigInteger.valueOf(n).multiply(scale);

        BigInteger x = bigN.divide(BigInteger.TWO);

        while (true) {
            BigInteger nextX = (x.add(bigN.divide(x))).divide(BigInteger.TWO);
            if (nextX.compareTo(x) >= 0) return x;
            x = nextX;
        }
    }

    static int decimalSum(BigInteger n) {
        // System.out.println(n.toString());
        return Arrays.stream(n.toString().substring(0, 100).split("")).mapToInt(Integer::parseInt).sum();
    }

    public static void main(String[] args) {
        long sum = 0;
        for (int i = 2; i < 100; ++i) {
            if (Math.sqrt(i) == (int)Math.sqrt(i)) continue;
            sum += decimalSum(newtonMethod(i, 100));
        }
        System.out.println(sum);
    }

}