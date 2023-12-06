import java.math.BigInteger;

public class StaticPaths {

    public static void main(String[] args) {
        int gridSize = 20;
        BigInteger paths = factorial(2 * gridSize).divide(factorial(gridSize).multiply(factorial(gridSize)));
        System.out.println(paths);
    }

    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
