public class NonAbundantSums {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i < 28123; ++i) {
            sum += (!isAbundantSum(i)) ? i : 0;
        }
        System.out.println(sum);
    }

    public static boolean isAbundantSum(int n) {
        for (int i = 1; i <= n / 2; ++i) {
            if (isAbundant(i) && isAbundant(n - i)) return true;
        }
        return false;
    }

    public static boolean isAbundant(int n) {
        int divisorSum = 1;
        for (int i = 2; i * i <= n; ++i) {
            divisorSum += (n % i == 0) ? (n / i == i) ? i : i + (n / i) : 0;
        }
        return divisorSum > n;
    }

}