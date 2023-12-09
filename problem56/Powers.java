import java.math.BigInteger;

public class Powers {

    public static void main(String[] args) {
        int max = 0;
        for (int a = 1; a < 100; ++a) {
            for (int b = 1; b < 100; ++b) {
                max = Math.max(digitSum(BigInteger.valueOf(a).pow(b), 0), max);
            }
        }
        System.out.println(max);
    }

    static int digitSum(BigInteger n, int sum) {
        return (n.equals(BigInteger.ZERO)) ? sum : digitSum(n.divide(BigInteger.TEN), sum + Integer.parseInt(n.mod(BigInteger.TEN).toString()));
    }

}