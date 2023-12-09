import java.util.ArrayList;

public class CuriousFractions {

    static ArrayList<int[]> curiousFracs = new ArrayList<>();
    public static void main(String[] args) {
        for (int n = 10; n < 99; ++n) {
            for (int d = n + 1; d <= 99; ++d) {
                if (n % 10 == 0 || d % 10 == 0) continue;
                if (isCurious(n, d)) {
                    curiousFracs.add(new int[]{n,d});
                }
            }
        }
        int[] product = {1, 1};
        for (int[] frac : curiousFracs) {
            product[0] *= frac[0];
            product[1] *= frac[1];
        }
        System.out.println(trueSimplify(product[0], product[1])[1]);
    }

    static boolean isCurious(int a, int b) {
        int[] naive = naiveSimplify(a, b);
        if (naive[0] >= 10) return false;
        return Math.abs(((double)naive[0] / naive[1]) - ((double)a / b)) < 0.001; 
    }

    static int[] trueSimplify(int a, int b) {
        int gcf = a;
        while (a % gcf != 0 || b % gcf != 0) {
            --gcf;
        }
        return (gcf == 10) ? new int[]{a, b} : new int[]{a / gcf, b / gcf};
    }

    static int[] naiveSimplify(int a, int b) {
        if (a % 10 == b % 10) {
            return new int[]{a / 10, b / 10};
        } else if (a % 10 == b / 10) {
            return new int[]{a / 10, b % 10};
        } else if (a / 10 == b / 10) {
            return new int[]{a % 10, b % 10};
        } else if (a / 10 == b % 10) {
            return new int[]{a % 10, b / 10};
        } else {
            return new int[]{a, b};
        }
    }

}