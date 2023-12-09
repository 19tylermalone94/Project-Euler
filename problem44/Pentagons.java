public class Pentagons {

    public static void main(String[] args) {
        for (int i = 1; i < 10000; ++i) {
            for (int j = 1; j < 10000; ++j) {
                if (i == j) continue;
                if (isPentagonal(pentagon(i) + pentagon(j)) && isPentagonal(Math.abs(pentagon(i) - pentagon(j)))) {
                    System.out.println(Math.abs(pentagon(i) - pentagon(j)));
                    break;
                }
            }
        }
    }

    // y = n(3n - 1) / 2
    // 3n^2 - n - 2y
    // x = max((1 +- sqrt(1 + 24y)) / 6)
    static boolean isPentagonal(int n) {
        double sol = Math.max((1 + Math.sqrt(1 + 24 * n)) / 6, (1 - Math.sqrt(1 + 24 * n)) / 6);
        return sol == (int) sol;
    }

    static int pentagon(int n) {
        return n * (3*n - 1) / 2;
    }

}