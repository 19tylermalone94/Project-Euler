import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class BOP {

    private static final MathContext mc = new MathContext(15, RoundingMode.HALF_EVEN);

    public static BigDecimal[] solve(BigDecimal[][] A, BigDecimal[] B) {
        int N = B.length;
        for (int p = 0; p < N; p++) {
            // Find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (A[i][p].abs().compareTo(A[max][p].abs()) > 0) {
                    max = i;
                }
            }
            BigDecimal[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            BigDecimal t = B[p];
            B[p] = B[max];
            B[max] = t;

            // Pivot within A and B
            for (int i = p + 1; i < N; i++) {
                BigDecimal alpha = A[i][p].divide(A[p][p], mc);
                B[i] = B[i].subtract(alpha.multiply(B[p], mc));
                for (int j = p; j < N; j++) {
                    A[i][j] = A[i][j].subtract(alpha.multiply(A[p][j], mc));
                }
            }
        }

        // Back substitution
        BigDecimal[] x = new BigDecimal[N];
        for (int i = N - 1; i >= 0; i--) {
            BigDecimal sum = BigDecimal.ZERO;
            for (int j = i + 1; j < N; j++) {
                sum = sum.add(A[i][j].multiply(x[j], mc));
            }
            x[i] = B[i].subtract(sum).divide(A[i][i], mc);
        }
        return x;
    }

    public static void main(String[] args) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int n = 1; n <= 10; ++n) {
            BigDecimal[] B = new BigDecimal[n];
            for (int i = 1; i <= n; ++i) {
                B[i - 1] = new BigDecimal(f(i));
            }
            BigDecimal[][] A = new BigDecimal[B.length][B.length];
            for (int i = 0; i < B.length; ++i) {
                for (int j = 0; j < B.length; ++j) {
                    A[i][j] = new BigDecimal(Math.pow(i + 1, B.length - j - 1));
                }
            }
            BigDecimal[] sol = solve(A, B);
            BigDecimal nextTerm = BigDecimal.ZERO;
            for (int i = 0; i < sol.length; ++i) {
                nextTerm = nextTerm.add(sol[i].multiply(new BigDecimal(Math.pow(B.length + 1, sol.length - i - 1))));
            }
            sum = sum.add(nextTerm);
        }
        System.out.println(sum);
    }

    static long f(long n) {
        int sign = -1;
        long y = 0;
        for (long ex = 0; ex <= 10; ++ex) {
            y += Math.pow(n, ex) * -sign;
            sign *= -1;
        }
        return y;
    }

}