

public class Periods {

    public static int periodLength(int n) {
        int a0 = (int)Math.sqrt(n);
        if (a0 * a0 == n) {
            return 0; // perfect squares aren't periodic
        }
        int period = 0;
        int a = a0, m = 0, d = 1;

        while (a != 2 * a0) {
            m = d * a - m;
            d = (n - m * m) / d;
            a = (a0 + m) / d;
            ++period;
        }
        return period;
    }

    public static void main(String[] args) {
        int count = 0;
        for (int n = 1; n <= 10000; ++n) {
            if (periodLength(n) % 2 == 1) ++count;
        }
        System.out.println(count);
    }

}