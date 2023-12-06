public class Shapes {

    public static void main(String[] args) {
        for (long i = 40756; i > 0; ++i) {
            if (isTriangle(i) && isPentagonal(i) && isHexagon(i)) {
                System.out.println(i);
                break;
            }
        }
    }

    // T = n(n + 1) / 2
    static boolean isTriangle(long n) {
        double sol = Math.max((-1 + Math.sqrt(1 + 8*n)) / 2, (-1 - Math.sqrt(1 + 8*n)) / 2);
        return sol == (int)sol;
    }

    // P = n(3n - 1) / 2
    static boolean isPentagonal(long n) {
        double sol = Math.max((1 + Math.sqrt(1 + 24 * n)) / 6, (1 - Math.sqrt(1 + 24 * n)) / 6);
        return sol == (int) sol;
    }

    // H = n(2n - 1)
    static boolean isHexagon(long n) {
        double sol = Math.max((1 + Math.sqrt(1 + 8*n)) / 4, (1 - Math.sqrt(1 + 8*n)) / 4);
        return sol == (int)sol;
    }

}