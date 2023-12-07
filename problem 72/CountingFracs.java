
public class CountingFracs {

    static int totient(int n) {
        int result = n;
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }

    public static void main(String[] args) {
        long sum = 0;
        for (int i = 2; i <= 1000000; ++i) {
            sum += totient(i);
        }
        System.out.println(sum);
    }

}