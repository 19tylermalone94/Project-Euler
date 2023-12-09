public class CountingFracs {

    static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int count = 0;
        for (int d = 12000; d > 2; --d) {
            for (int n = 1; n < 12000; ++n) {
                double val = (double)n / d;
                if (val <= 1 / 3.0) continue;
                if (val >= 1 / 2.0) break;
                count += (gcd(n, d) == 1) ? 1 : 0;
            }
        }
        System.out.println(count);
    }
}
